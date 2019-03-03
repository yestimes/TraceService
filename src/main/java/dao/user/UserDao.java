package dao.user;

import bean.user.info.User;
import bean.user.result.UserResult;
import org.apache.ibatis.session.SqlSession;
import utils.argsCheck.StringChecker;
import utils.database.DBUtil;

public class UserDao {

    public static UserResult userLogin(User user){
        UserResult result = new UserResult();

        if (StringChecker.isStringBlank(user.getUsername())){
            result.onFail(400, "参数错误：用户名为空", "");
        }else {
            // 获取Session连接
            SqlSession session = DBUtil.getSession().openSession();
            // 获取Mapper
            IUserMapper userMapper = session.getMapper(IUserMapper.class);
            try {
                user = userMapper.getUserByName(user.getUsername());
                if(user!=null){
                    if (user.getPassword().equals(user.getPassword())){
                        result.onSuccess("欢迎您，" + user.getRealname(), "console.html");
                    }else {
                        result.onFail(403, "密码不正确", "");
                    }
                }else {
                    result.onFail(404, "用户不存在", "");
                }
            } finally {
                session.commit();
            }
        }


        return result;
    }
}
