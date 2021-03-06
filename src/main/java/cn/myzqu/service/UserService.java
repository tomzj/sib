package cn.myzqu.service;

import cn.myzqu.dto.PageDTO;
import cn.myzqu.dto.UserDTO;
import cn.myzqu.pojo.User;

import java.util.List;

/**
 * 用户服务接口
 * Created by 的川 on 2018/5/8.
 */
public interface UserService {

    /**
     * 登录处理
     * @param code
     * @param password
     * @return
     */
    User login(String code,String password);

    /**
     * 核对密码是否正确
     * @param id
     * @param password
     * @return
     */
    Boolean checkPassword(String id,String password);

    /**
     * 根据微信openId查询用户信息
     * @param wxid
     * @return
     */
    UserDTO findByWxId(String wxid);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    UserDTO findById(String id);


    /**
     * 注册、新增用户
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 批量添加用户
     * @param users
     * @return
     */
    Boolean batchAdd(List<User> users) ;

    /**
     * 检测用户id是否存在，存在返回true，不存在返回false
     * @param id
     * @return
     */
    Boolean checkId(String id);

    /**
     * 锁定用户账号
     * @param id
     * @return
     */
    Boolean lock(String id);

    /**
     * 解除锁定用户账号
     * @param id
     * @return
     */
    Boolean unLock(String id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Boolean update(User user);

    /**
     * 修改账号密码
     * @param id
     * @param password
     * @return
     */
    Boolean updatePassword(String id,String password);

    /**
     * 分页查询所有用户信息
     * @param pageNum 当前页数，表示从第 pageNum 页开始查询
     * @param pageSize 每页记录数，同Mysql中的limit
     * @return
     */
    PageDTO findAll(int pageNum,int pageSize);

    /**
     * 定时更新用户积分
     * @return
     */
    Boolean calUserValue();

}
