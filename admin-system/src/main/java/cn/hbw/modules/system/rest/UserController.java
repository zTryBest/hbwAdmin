package cn.hbw.modules.system.rest;

import cn.hbw.modules.system.domain.User;
import cn.hbw.modules.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassNameUserController
 * @Description
 * @Author zzj
 * @Date2020/10/18
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> getUserList(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody User user){
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
