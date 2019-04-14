//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.order.purchase;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IPurchaseMapper {
    @Insert({"INSERT INTO t_order(ppid, uid) VALUES(#{ppid}, #{uid})"})
    void insertOrder(@Param("ppid") int var1, @Param("uid") int var2);

    @Insert({"INSERT INTO t_keys(uid, ppid, appkey, status) VALUES(#{uid}, #{ppid}, #{appkey}, 1) "})
    int backupKey(@Param("uid") int var1, @Param("ppid") int var2, @Param("appkey") String var3);

    @Select({"SELECT ppid FROM t_pay_package WHERE pid = #{pid}"})
    int getPPID(int var1);

    @Select({"SELECT COUNT(kid) FROM t_keys WHERE t_keys.uid = #{uid} and ppid = #{ppid}"})
    int getKeysCount(@Param("uid") int var1, @Param("ppid") int var2);
}
