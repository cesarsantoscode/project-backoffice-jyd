package pe.due.cibertec.project_backoffice_jyd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.due.cibertec.project_backoffice_jyd.dto.UserDetailDto;
import pe.due.cibertec.project_backoffice_jyd.dto.UserDto;
import pe.due.cibertec.project_backoffice_jyd.response.*;
import pe.due.cibertec.project_backoffice_jyd.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-user")
public class ManageUserApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindUsersResponse findUsers() {

        try {
            List<UserDto> users = manageService.getAllUsers();
            if (!users.isEmpty())
                return new FindUsersResponse("01", null, users);
            else
                return new FindUsersResponse("02", "Users not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindUsersResponse("99", "An error ocurred, please try again", null);

        }

    }

    @GetMapping("/detail")
    public FindUserResponse findUser(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            Optional<UserDetailDto> optional = manageService.getUserById(Integer.parseInt(id));
            return optional.map(user ->
                    new FindUserResponse("01", null, user)
            ).orElse(
                    new FindUserResponse("02", "User not found", null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new FindUserResponse("99", "An error ocurred, please try again", null);

        }

    }

    @PutMapping("/update")
    public UpdateUserResponse updateUser(@RequestBody UserDto userDto) {

        try {
            if (manageService.updateUser(userDto))
                return new UpdateUserResponse("01", null);
            else
                return new UpdateUserResponse("02", "User not found");

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateUserResponse("99", "An error ocurred, please try again");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteUserResponse deleteUser(@PathVariable String id) {

        try {
            if (manageService.deleteUserById(Integer.parseInt(id)))
                return new DeleteUserResponse("01", null);
            else
                return new DeleteUserResponse("02", "User not found");

        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteUserResponse("99", "An error ocurred, please try again");

        }

    }

    @PostMapping("/create")
    public CreateUserResponse createUser(@RequestBody UserDetailDto userDetailDto) {

        try {
            if (manageService.addUser(userDetailDto))
                return new CreateUserResponse("01", null);
            else
                return new CreateUserResponse("02", "User already exists");

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateUserResponse("99", "An error ocurred, please try again");

        }

    }

}
