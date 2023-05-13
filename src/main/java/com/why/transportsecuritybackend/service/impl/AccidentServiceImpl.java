package com.why.transportsecuritybackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.why.transportsecuritybackend.common.response.Result;
import com.why.transportsecuritybackend.common.selfenum.AccidentResolveStateEnum;
import com.why.transportsecuritybackend.common.utils.BaiduMapUtils;
import com.why.transportsecuritybackend.common.utils.NumberUtils;
import com.why.transportsecuritybackend.common.utils.OutputFileUtils;
import com.why.transportsecuritybackend.common.utils.accident.AccidentUtils;
import com.why.transportsecuritybackend.dao.pojo.*;
import com.why.transportsecuritybackend.entity.dto.*;
import com.why.transportsecuritybackend.manager.AccidentManager;
import com.why.transportsecuritybackend.manager.AxManager;
import com.why.transportsecuritybackend.manager.AyManager;
import com.why.transportsecuritybackend.manager.VehicleManager;
import com.why.transportsecuritybackend.service.AccidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 事故信息业务接口实现类
 *
 * @author why
 * @date 2023/05/05 23:43
 **/
@Slf4j
@Service
public class AccidentServiceImpl implements AccidentService {

    private static final boolean IS_BOUNCE = true;
    private static final String HIGH_LEVEL = "严重损伤";
    private static final String LOW_LEVEL = "一般损伤";
    private static final String POP = "弹开";
    private static final Integer NONE_RESOLVE = 0;

    private final AccidentManager accidentManager;
    private final VehicleManager vehicleManager;
    private final AxManager axManager;
    private final AyManager ayManager;

    @Autowired
    public AccidentServiceImpl(
            AccidentManager accidentManager,
            VehicleManager vehicleManager,
            AxManager axManager,
            AyManager ayManager
    ) {
        this.accidentManager = accidentManager;
        this.vehicleManager = vehicleManager;
        this.axManager = axManager;
        this.ayManager = ayManager;
    }

    @Override
    public Result selectAccidentHome(Integer state, Integer index, Integer limit) {
        List<Accident> accidents = accidentManager.selectAccident(state, index, limit);
        if (CollectionUtils.isEmpty(accidents)) {
            return Result.success();
        }
        List<AccidentHomeDTO> accidentHomeDTOS = new ArrayList<>();
        accidents.forEach(accident -> {
            AccidentHomeDTO accidentHomeDTO = new AccidentHomeDTO();
            accidentHomeDTO.setDate(accident.getUpdateTime());
            String accidentLevel = getAccidentLevel(accident);
            accidentHomeDTO.setAccidentLevel(accidentLevel);
            accidentHomeDTO.setResolveState(state);
            Vehicle vehicle = vehicleManager.selectById(accident.getVehicleId());
            accidentHomeDTO.setVehicleNumber(vehicle.getVehicleNumber());
            accidentHomeDTOS.add(accidentHomeDTO);
        });
        return Result.success(accidentHomeDTOS);
    }

    @Override
    public List<LocalDate> get30Date() {
        LocalDate localDate = LocalDate.now().minusDays(0);
        LocalDate afterDate = localDate.minusDays(30);
        // 获取倒数30天
        List<LocalDate> localDates = new ArrayList<>(30);

        for (LocalDate currentDate = afterDate;
             currentDate.isBefore(localDate) ||
                     currentDate.isEqual(localDate);
             currentDate = currentDate.plusDays(1)) {
            localDates.add(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth()));
        }
        return localDates;
    }

    @Override
    public List<Integer> getNCount(List<LocalDate> localDates) {
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < localDates.size(); i++) {
            Integer count = accidentManager.selectDateCount(localDates.get(i));
            counts.add(count);
        }
        return counts;
    }

    @Override
    public AccidentStateCountDTO getStateCount() {
        Integer already = accidentManager.selectStateCount(AccidentResolveStateEnum.ALREADY_STATE.getCode());
        Integer working = accidentManager.selectStateCount(AccidentResolveStateEnum.WORKING_STATE.getCode());
        Integer none = accidentManager.selectStateCount(AccidentResolveStateEnum.NONE_STATE.getCode());
        Integer total = already + working + none;
        return new AccidentStateCountDTO(total, already, working, none);
    }

    @Override
    public AccelerationDTO getAx(Integer accidentId) {
        List<Ax> axes = axManager.selectAx(accidentId);
        AccelerationDTO accelerationDTO = new AccelerationDTO();
        accelerationDTO.setDate(axes.stream().map(Ax::getCreateTime).collect(Collectors.toList()));
        accelerationDTO.setValue(axes.stream().map(Ax::getAx).collect(Collectors.toList()));
        return accelerationDTO;
    }

    @Override
    public AccelerationDTO getAy(Integer accidentId) {
        List<Ay> ays = ayManager.selectAy(accidentId);
        AccelerationDTO accelerationDTO = new AccelerationDTO();
        accelerationDTO.setDate(ays.stream().map(Ay::getCreateTime).collect(Collectors.toList()));
        accelerationDTO.setValue(ays.stream().map(Ay::getAy).collect(Collectors.toList()));
        return accelerationDTO;
    }

    @Override
    public Result AccidentInfo(Integer index, Integer limit) {
        List<Accident> accidents = accidentManager.selectAccidentInfo(index, limit, AccidentResolveStateEnum.NONE_STATE.getCode());
        Integer total = accidentManager.selectCount();
        List<AccidentInfoDTO> accidentInfoDTOS = new ArrayList<>(accidents.size());
        accidents.forEach(accident -> {
            Vehicle vehicle = vehicleManager.selectById(accident.getVehicleId());
            String address = BaiduMapUtils.getAddress(accident.getLng(), accident.getLat());
            AccidentInfoDTO accidentInfoDTO = new AccidentInfoDTO(
                    accident.getId(),
                    vehicle.getVehicleOwner(),
                    vehicle.getVehicleNumber(),
                    vehicle.getVehicleType(),
                    address,
                    accident.getCreateTime(),
                    accident.getResolveState()
            );
            accidentInfoDTOS.add(accidentInfoDTO);
        });
        PageDTO pageDTO = new PageDTO(total, index, limit, accidentInfoDTOS);
        return Result.success(pageDTO);
    }

    @Override
    public Result getAccidentVehicle(Integer accidentId) {
        Accident accident = accidentManager.selectAccidentById(accidentId);
        Vehicle vehicle = vehicleManager.selectById(accident.getVehicleId());
        AccidentVehicleDTO accidentVehicleDTO = new AccidentVehicleDTO();
        BeanUtil.copyProperties(vehicle, accidentVehicleDTO);
        return Result.success(accidentVehicleDTO);
    }

    @Override
    public Result getAccidentDetails(Integer accidentId) {
        Accident accident = accidentManager.selectAccidentById(accidentId);
        String accidentLevel = getAccidentLevel(accident);
        AccidentDetailsDTO accidentDetailsDTO = new AccidentDetailsDTO(
                accidentLevel,
                POP,
                accident.getCreateTime(),
                accident.getResolveState()
        );
        return Result.success(accidentDetailsDTO);
    }

    @Override
    public Result getAccidentAddress(Integer accidentId) {
        Accident accident = accidentManager.selectAccidentById(accidentId);
        String address = BaiduMapUtils.getAddress(accident.getLng(), accident.getLat());
        AccidentAddressDTO accidentAddressDTO = new AccidentAddressDTO(
                accident.getLng(),
                accident.getLat(),
                address
        );
        return Result.success(accidentAddressDTO);
    }

    @Override
    public Result updateAccidentState(Integer state, Integer accidentId) {
        accidentManager.updateAccidentState(state, accidentId);
        return Result.success();
    }

    @Override
    public void downloadAccident(Integer accidentId, HttpServletResponse response) {
        Accident accident = accidentManager.selectAccidentById(accidentId);
        Vehicle vehicle = vehicleManager.selectById(accident.getVehicleId());
        List<Ax> axes = axManager.selectAx(accidentId);
        List<Double> axList = axes.stream().map(Ax::getAx).collect(Collectors.toList());
        List<Ay> ays = ayManager.selectAy(accidentId);
        List<Double> ayList = ays.stream().map(Ay::getAy).collect(Collectors.toList());
        String accidentLevel = getAccidentLevel(accident);

        String address = BaiduMapUtils.getAddress(accident.getLng(), accident.getLat());
        String direction = getDirection(axList, ayList);
        VehicleType vehicleType = vehicleManager.selectVehicleTypeById(vehicle.getVehicleType());
        LocalDateTime createTime = accident.getCreateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String accidentDate = dtf.format(createTime);
        AccidentExcelDTO accidentExcelDTO = new AccidentExcelDTO(
                vehicle.getVehicleOwner(),
                vehicle.getVehicleNumber(),
                vehicleType.getVehicleType(),
                accidentLevel,
                accidentLevel,
                accidentDate,
                accident.getLng(),
                accident.getLat(),
                address,
                direction,
                POP,
                axList,
                ayList
        );
        List<AccidentExcelDTO> data = new ArrayList<>();
        data.add(accidentExcelDTO);
        OutputFileUtils.outputFile(data, response);
    }

    @Override
    public void insert(String info) {
        String[] infos = info.split("\n");

        // 处理经纬度
        String[] lngStr = infos[1].split(" ");
        String[] latStr = infos[2].split(" ");
        double lng = Double.valueOf(lngStr[0].trim().substring(10));
        double lat = Double.valueOf(latStr[0].trim().substring(9));

        // 处理车牌号
        String vehicleNumber = infos[4].trim().substring(4, 12);

        // 处理ax，ay
        String axStr = infos[4].trim().substring(14);
        String ayStr = infos[5].trim().substring(3);
        String[] axs = axStr.split(",");
        // 处理ax
        List<Double> axList = new ArrayList<>();
        for (int i = 0; i < axs.length; i++) {
            axList.add(NumberUtils.strToDoubleValue(axs[i].trim()));
        }

        // 处理ay
        String[] ays = ayStr.trim().split(",");
        List<Double> ayList = new ArrayList<>();
        for (int i = 0; i < ays.length; i++) {
            ayList.add(NumberUtils.strToDoubleValue(ays[i].trim()));
        }

        // 查出车辆id
        Integer vehicleId = vehicleManager.selectVehicleByNumber(vehicleNumber);

        if (vehicleId != null) {
            // 添加accidentInfo
            Accident accidentInfoNew = new Accident(lng, lat, vehicleId, NONE_RESOLVE);
            Integer accidentId = accidentManager.insertAccident(accidentInfoNew);
            // 添加ax
            Iterator<Double> iteratorAx = axList.iterator();
            List<Ax> axes = new ArrayList<>();
            while (iteratorAx.hasNext()) {
                Double x = iteratorAx.next();
                Ax ax1 = new Ax(x, accidentInfoNew.getId());
                axes.add(ax1);
            }
            axManager.insertBatch(axes);
            // 添加ay
            Iterator<Double> iteratorAy = ayList.iterator();
            List<Ay> aysInsert = new ArrayList<>();
            while (iteratorAy.hasNext()) {
                Double y = iteratorAy.next();
                Ay ay1 = new Ay(y, accidentInfoNew.getId());
                aysInsert.add(ay1);
            }
            ayManager.insertBatch(aysInsert);
        } else {
            log.error("该车辆未注册！");
        }
    }

    /**
     * 获取碰撞方向
     *
     * @param axList ax
     * @param ayList ay
     */
    private static String getDirection(List<Double> axList, List<Double> ayList) {
        double vx = AccidentUtils.vx(axList);
        double vy = AccidentUtils.vy(ayList);
        double pdof = AccidentUtils.pdof(vx, vy);
        int direction = AccidentUtils.direction(pdof);
        // 1：正面碰撞，2：左侧碰撞，3：追尾碰撞，4：右侧碰撞
        switch (direction) {
            case 1:
                return "正面碰撞";
            case 2:
                return "左侧碰撞";
            case 3:
                return "追尾碰撞";
            case 4:
                return "右侧碰撞";
            default:
                break;
        }
        return null;
    }

    /**
     * 获取伤情等级
     *
     * @param accident 事故信息
     * @return 伤情等级
     */
    private String getAccidentLevel(Accident accident) {
        List<Ax> axes = axManager.selectAx(accident.getId());
        List<Double> axList = axes.stream().map(Ax::getAx).collect(Collectors.toList());
        List<Ay> ays = ayManager.selectAy(accident.getId());
        List<Double> ayList = ays.stream().map(Ay::getAy).collect(Collectors.toList());
        boolean accidentLevelBoolean = AccidentUtils.driverDegreeAccidentLevel(axList, ayList, IS_BOUNCE);
        String accidentLevel;
        if (accidentLevelBoolean) {
            accidentLevel = HIGH_LEVEL;
        } else {
            accidentLevel = LOW_LEVEL;
        }
        return accidentLevel;
    }
}
