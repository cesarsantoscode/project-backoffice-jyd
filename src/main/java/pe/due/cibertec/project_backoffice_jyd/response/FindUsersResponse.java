package pe.due.cibertec.project_backoffice_jyd.response;

import pe.due.cibertec.project_backoffice_jyd.dto.UserDto;

public record FindUsersResponse(String code,
                                String error,
                                Iterable<UserDto> users) {

}
