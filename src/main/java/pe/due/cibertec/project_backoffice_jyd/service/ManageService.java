package pe.due.cibertec.project_backoffice_jyd.service;

import pe.due.cibertec.project_backoffice_jyd.dto.UserDetailDto;
import pe.due.cibertec.project_backoffice_jyd.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<UserDto> getAllUsers() throws Exception;

    Optional<UserDetailDto> getUserById(int id) throws Exception;

    boolean updateUser(UserDto userDto) throws Exception;

    boolean deleteUserById(int id) throws Exception;

    boolean addUser(UserDetailDto userDetailDto) throws Exception;

}
