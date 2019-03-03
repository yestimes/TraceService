package dao.user;

import bean.user.info.User;
import org.apache.ibatis.annotations.Select;

public interface IUserMapper {
    @Select("select * from t_user where username = #{username}")
    public User getUserByName(String username);

    @Select("select * from t_user, t_userctl where username = #{username} and t_user.uid = t_userctl.uid")
    public User getAdminByName(String username);


}
