package cn.myzqu.controller;

/**
 * Created by Chrky on 2018/5/10.
 */

import cn.myzqu.dto.BankDTO;
import cn.myzqu.dto.PageDTO;
import cn.myzqu.enums.ResultEnum;
import cn.myzqu.pojo.QuestionBank;
import cn.myzqu.service.QuestionBankService;
import cn.myzqu.utils.ResultVOUtil;
import cn.myzqu.vo.Result;
import cn.myzqu.vo.ResultVO;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/questionBank")
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 创建题库
     * @param questionBank
     * @return
     */
    @PostMapping("/info")
    public Result addQuestionBank(@Valid QuestionBank questionBank) {
        if (questionBankService.add(questionBank))
            return ResultVOUtil.success();
        return ResultVOUtil.error(ResultEnum.BANK_CREATE_FAIL);
    }

    /**
     * 根据题库id删除题库
     * @param id
     * @return
     */
    @DeleteMapping("/info")
    public Result deleteQuestionBank(String id) {
        if (questionBankService.deleteById(id)) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.BANK_DELETE_FAIL);
    }
    /**
     * 修改题库信息
     * 只能用x-www-form-urlencoded
     * @param questionBank
     * @return
     */
    @PutMapping("/info")
    public Result updateQuestionbank(QuestionBank questionBank) {
        if (questionBankService.updateById(questionBank))
            return ResultVOUtil.success();
        else
            return ResultVOUtil.error(ResultEnum.BANK_UPDATE_FAIL);
    }


    /**
     * 题库排序显示
     * @param condition  说明:frequency 按人数排序 star_level按星级排序 否则按更新时间排序
     * @return
     */
    @GetMapping("/infoSort")
    public Result getBankByNumber(@RequestParam Map<String,Object> condition,
                                  @RequestParam(value="page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "size",defaultValue = "10") Integer size) {
        PageDTO pageDTO = questionBankService.selectSort(condition,page,size);
        if (pageDTO==null)
            return ResultVOUtil.error(ResultEnum.BANK_NOT_EXIST);
        else
            return ResultVOUtil.success(pageDTO);
    }

    /**
     * 根据题库id或题库标题或用户id或类目名称查询题库信息
     * @param condition  说明:  id or title or userId or categoryName
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/info")
    public Result get(@RequestParam Map<String,Object> condition,
                                    @RequestParam(value="page",defaultValue = "1") Integer page,
                                    @RequestParam(value = "size",defaultValue = "10") Integer size) {
        PageDTO pageDTO = questionBankService.select(condition,page,size);
        if (pageDTO==null)
            return ResultVOUtil.error(ResultEnum.BANK_NOT_EXIST);
        else
            return ResultVOUtil.success(pageDTO);
    }

    /**
     * 根据用户id查询题库
     * @param id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/infoByUserId")
    public Result getByUserId(@RequestParam String id,
                      @RequestParam(value="page",defaultValue = "1") Integer page,
                      @RequestParam(value = "size",defaultValue = "10") Integer size) {
        PageDTO pageDTO = questionBankService.findByUserId(id,page,size);
        if (pageDTO==null)
            return ResultVOUtil.error(ResultEnum.BANK_NOT_EXIST);
        else
            return ResultVOUtil.success(pageDTO);
    }

    /**
     * 根据类目名称查询题库
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/infoTypeName")
    public Result getTypeName(@RequestParam String name,
                              @RequestParam(value="page",defaultValue = "1") Integer page,
                              @RequestParam(value = "size",defaultValue = "10") Integer size) {
        PageDTO pageDTO = questionBankService.findByTypeName(name,page,size);
        if (pageDTO==null)
            return ResultVOUtil.error(ResultEnum.BANK_NOT_EXIST);
        else
            return ResultVOUtil.success(pageDTO);
    }
}
