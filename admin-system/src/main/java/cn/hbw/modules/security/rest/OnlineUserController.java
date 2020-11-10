package cn.hbw.modules.security.rest;

import cn.hbw.logging.annotation.Log;
import cn.hbw.modules.security.service.OnlineUserService;
import cn.hbw.modules.security.service.dto.OnlineUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * ClassName：cn.hbw.modules.security.rest.OnlineUserController
 * Description：在线用户控制器类
 * Copyright © 2020 hbw
 *
 * @author 邹志杰
 * @version v1.0
 * @date 2020/11/10 13:42
 **/
@RestController
@Slf4j
@RequestMapping("/online")
@RequiredArgsConstructor
public class OnlineUserController {
    private final OnlineUserService onlineUserService;

    @Log("获取在线用户信息")
    @GetMapping("/getAll")
    @PreAuthorize("@hbw.check()")
    public ResponseEntity<Object> getAllOnlineUser(String filter, Pageable pageable){
         Map<String, Object> onlineUser = onlineUserService.getAll(filter,pageable);
        return ResponseEntity.ok(onlineUser);
    }
}
