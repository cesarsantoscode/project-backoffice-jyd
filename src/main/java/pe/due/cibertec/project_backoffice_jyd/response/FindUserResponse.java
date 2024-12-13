package pe.due.cibertec.project_backoffice_jyd.response;

import pe.due.cibertec.project_backoffice_jyd.dto.UserDetailDto;

public record FindUserResponse(String code,
                               String error,
                               UserDetailDto user) {

}
