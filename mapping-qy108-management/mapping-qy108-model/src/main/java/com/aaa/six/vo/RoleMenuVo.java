package com.aaa.six.vo;

import com.aaa.six.vo.RoleVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Company AAA软件教育
 * @Title mapping-qy108
 * @Author hhy
 * @Version 0.1.0
 * @Date Create in 2020/5/23 15:08
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleMenuVo  {

    /**
     * 权限id（批量修改）
     */
    private Object[] ids;

    /**
     * roleVo实体类（修改角色描述）
     */
    private RoleVo roleVo;

}
