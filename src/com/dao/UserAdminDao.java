package com.dao;

import com.entity.UserAdmin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAdminDao extends PagingAndSortingRepository<UserAdmin, String> {

    /**
     * 根据用户名和密码查询用户
     *
     * @param userName 用户名
     * @param Pwd      密码
     * @return 查询数据
     */
    List<UserAdmin> findByUserNameAndPwd(String userName, String Pwd);

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 查询数据
     */
    List<UserAdmin> findByUserName(String userName);


    /**
     * 根据id删除用户，不能删除超级用户
     *
     * @param id 需要删除的id
     * @return 影响的行数
     */
    @Modifying
    @Query("delete from UserAdmin m where m.id=?1 and m.superUser=?2")
    int deleteByIdAndSuper(String id, boolean isSuper);
}
