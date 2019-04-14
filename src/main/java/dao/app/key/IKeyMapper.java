//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.app.key;

import bean.app.res.KeyUsage;
import bean.order.info.Key;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IKeyMapper {
    @Select({"SELECT appKey, count FROM t_keys WHERE appKey = #{key} and status = 1"})
    KeyUsage getKeyUsage(String var1);

    @Select({"SELECT * FROM t_keys WHERE uid = #{uid}"})
    List<Key> getKeysByUID(@Param("uid") int var1);

    @Insert({"UPDATE t_keys SET count = #{count} where uid = #{uid} and appKey = #{key}"})
    int updateAPIUsage(@Param("count") int var1, @Param("uid") int var2, @Param("key") String var3);
}
