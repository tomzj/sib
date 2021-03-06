package cn.myzqu.service;

import cn.myzqu.dto.ChoiceDTO;
import cn.myzqu.dto.PageDTO;
import cn.myzqu.pojo.ChoiceQuestion;

import java.util.List;
import java.util.Map;

/**
 * Created by Chrky on 2018/5/14.
 */
public interface ChoiceQuestionService {

    /**
     * 根据题目id查询题目
     * @param id
     * @return
     */
    ChoiceQuestion findById(String id);

    /**
     * 根据题目查询题目详情
     * @param id
     * @return
     */
    ChoiceQuestion findByQuestion(String id);

    /**
     * 添加题目
     * @param choiceQuestion
     * @return
     */
    Boolean add(ChoiceQuestion choiceQuestion);


    /**
     * 根据题目id删除题目
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 根据题库id查询题目
     * @param id
     * @return
     */
    List<ChoiceDTO> findByBankId(String id,String userId);

    /**
     * 根据题目id修改题目
     * @param choiceQuestion
     * @return
     */
    Boolean updateById(ChoiceQuestion choiceQuestion);

    /**
     * 综合查询(根据题目id，题目，答案，分析,用户id，题库标题模糊搜索）
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageDTO find(Map<String,Object> map,int pageNum, int pageSize);

    /**
     * 题目综合显示
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageDTO findSort(Map<String,Object> map, int pageNum, int pageSize);

    Boolean check(String id);//题目审核通过

    List<ChoiceDTO> findByUserBankId(String id);

    Boolean updateChoiceRating();//批量更新题目评分

    PageDTO findByUserId(String id,int pageNum, int pageSize);//根据用户id查询题目

    PageDTO findAllChoice(int pageNum, int pageSize);//获取所有题目

    PageDTO findCheatChoice(int pageNum, int pageSize);//显示所有待审核题目

    Boolean notCheck(String id);//题目审核不通过
}
