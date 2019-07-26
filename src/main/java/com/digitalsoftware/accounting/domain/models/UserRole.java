package com.digitalsoftware.accounting.domain.models;

import com.digitalsoftware.accounting.domain.generated.User;
import com.digitalsoftware.accounting.emun.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private Integer id;
    private User user;
    private List<RoleType> roleTypes;
}
