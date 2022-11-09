package ro.tuc.ds2020.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Role;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @GetMapping("devices/{id}")
    public ResponseEntity<List<DeviceDTO>> getUserDevices(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserDevices(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO){
        try{
            return ResponseEntity.ok(userService.create(userDTO));
        }catch(Exception e){
            System.out.println(e.toString());
            return ResponseEntity.ok(new UserDTO());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDto){
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.delete(id));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(userService.findByNameAndPassword(authDTO.getName(), authDTO.getPassword()));
    }

    @GetMapping("/consumption/{id}/{date}")
    public ResponseEntity<List<ConsumptionDTO>> getConsumption(@PathVariable("id") Long id,@PathVariable("date") Date date ){
        return ResponseEntity.ok(userService.getConsumption(id, date));
    }

    @PostMapping("/createRole")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody @Valid RoleDTO roleDto){
        try{
            return ResponseEntity.ok(userService.createRole(roleDto));
        }catch(Exception e){
            System.out.println(e.toString());
            return ResponseEntity.ok(new RoleDTO());
        }
    }

}
