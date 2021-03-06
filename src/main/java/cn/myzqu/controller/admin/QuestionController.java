package cn.myzqu.controller.admin;

import cn.myzqu.dto.PageDTO;
import cn.myzqu.enums.ResultEnum;
import cn.myzqu.pojo.ChoiceQuestion;
import cn.myzqu.service.ChoiceQuestionService;
import cn.myzqu.service.PointsService;
import cn.myzqu.utils.ResultVOUtil;
import cn.myzqu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chrky on 2018/5/14.
 */
@RestController("AdminQuestionController")
@RequestMapping("admin/question")
public class QuestionController {

    @Autowired
    private ChoiceQuestionService choiceQuestionService;

    @Autowired
    private PointsService pointsService;

    /**
     * 添加题目
     * @param choiceQuestion
     * @return
     */
    @PostMapping("/info")
    public Result addChoice(@Valid @RequestBody ChoiceQuestion choiceQuestion) {
        //添加题目
        if (choiceQuestionService.add(choiceQuestion))
            return ResultVOUtil.success();
        else
            return ResultVOUtil.error(ResultEnum.QUESTION_CREATE_FAIL);
    }

    /**
     * 根据题目id删除题目
     * @param id
     * @return
     */
    @DeleteMapping("/info/{id}")
    public Result deleteChoice(@PathVariable(value="id") String id) {
        System.out.println(id);
        //删除题目
        if (choiceQuestionService.deleteById(id))
            return ResultVOUtil.success();
        else
            return ResultVOUtil.error(ResultEnum.QUESTION_DELETE_FAIL);
    }

    /**
     * 根据题目id查询题目
     * @param id
     * @return
     */
    @GetMapping("/info/way/id")
    public Result getChoiceById(@RequestParam String id) {
        //创建choice对象
        ChoiceQuestion choiceQuestion = choiceQuestionService.findById(id);
        //根据题目id查询题目
        if (choiceQuestion == null)
            return ResultVOUtil.error(ResultEnum.QUESTION_NOT_EXIST);
        else
            return ResultVOUtil.success(choiceQuestion);
    }

    /**
     * 根据题目id修改题目
     * @param choiceQuestion
     * @return
     */
    @PutMapping("/info")
    public Result updateChoice(@RequestBody ChoiceQuestion choiceQuestion) {
        //修改题目信息
        if (choiceQuestionService.updateById(choiceQuestion))
            return ResultVOUtil.success();
        else
            return ResultVOUtil.error(ResultEnum.QUESTION_UPDATE_FAIL);
    }

    /**
     * 综合查询(根据题目id，题目，答案，分析,用户id，题库标题模糊搜索）
     * @param question userId title
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/info")
    public Result get(@RequestParam String question,
                      @RequestParam String userId,@RequestParam String title,
                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("question",question);
        condition.put("userId",userId);
        condition.put("title",title);
        PageDTO pageDTO = choiceQuestionService.find(condition, page, size);
        if (pageDTO == null)
            return ResultVOUtil.error(ResultEnum.QUESTION_NOT_EXIST);
        else
            return ResultVOUtil.success(pageDTO.getRows(),pageDTO.getTotal());

    }

    /**
     * 审核通过
     * @param id
     * @return
     */
    @PutMapping("/info/pass/{id}")
    public Result checkChoice(@PathVariable(value="id") String id) {
        //审核通过题目信息
        if (choiceQuestionService.check(id)) {
            //pointsService.checkChoice(id);
            return ResultVOUtil.success();
        } else
            return ResultVOUtil.error(ResultEnum.QUESTION_CHECK_FAIL);
    }

    /**
     * 审核不通过
     * @param id
     * @return
     */
    @PutMapping("/info/no/pass/{id}")
    public Result notCheck(@PathVariable(value="id") String id) {
        //审核不通过题目信息
        if (choiceQuestionService.notCheck(id)) {
            return ResultVOUtil.success();
        } else
            return ResultVOUtil.error(ResultEnum.QUESTION_CHECK_FAIL);
    }

    /**
     * 获取所有题目
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam(value="page",defaultValue = "1") Integer page,
                       @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageDTO data = choiceQuestionService.findAllChoice(page,size);
        if(data!=null) {
            return ResultVOUtil.success(data.getRows(),data.getTotal());
        }
        return ResultVOUtil.error(ResultEnum.QUESTIONLIST_EMPTY);
    }

    /**
     * 获取所有待审核题目
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/check/list")
    public Result getCheatChoice(@RequestParam(value="page",defaultValue = "1") Integer page,
                       @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageDTO data = choiceQuestionService.findCheatChoice(page,size);
        if(data!=null) {
            return ResultVOUtil.success(data.getRows(),data.getTotal());
        }
        return ResultVOUtil.error(ResultEnum.QUESTIONLIST_EMPTY);
    }
}
