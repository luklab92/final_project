package com.sda.final_project_wro27;

import com.sda.final_project_wro27.model.ProductCategoryEntity;
import com.sda.final_project_wro27.repository.ProductCategoryRepository;
import com.sda.final_project_wro27.repository.ProductRepository;
import com.sda.final_project_wro27.repository.UserRoleRepository;
import com.sda.final_project_wro27.repository.UserStatusRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DataSeed implements InitializingBean {
    private UserRoleRepository userRoleRepository;
    private UserStatusRepository userStatusRepository;
    private ProductCategoryRepository productCategoryRepository;

    public DataSeed(UserRoleRepository userRoleRepository, UserStatusRepository userStatusRepository, ProductCategoryRepository productCategoryRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userStatusRepository = userStatusRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public void afterPropertiesSet() {

        createRole(UserRole.Roles.ADMIN);
        createRole(UserRole.Roles.USER);
        createCategory(new ProductCategoryEntity("mleko"));
        createCategory(new ProductCategoryEntity("jablko"));
        createStatus(UserStatus.Status.NOT_ACCEPTED);
        createStatus(UserStatus.Status.ACCEPTED);

    }

    private void createCategory(ProductCategoryEntity productCategoryEntity) {
        if (!productCategoryRepository.existsByTitle(productCategoryEntity.getTitle())){
            productCategoryRepository.save(productCategoryEntity);
        }
    }

    private void createRole(UserRole.Roles role) {
        if( !userRoleRepository.roleExist(role.name())) {
            userRoleRepository.save(UserRole.apply(role));
        }
    }

    private void createStatus(UserStatus.Status status) {
        if( !userStatusRepository.statusExists(status.name())) {
            userStatusRepository.save(UserStatus.apply(status));
        }
    }


    //,metoda sie uruchamia jak urucuhomi sie spring context

}
