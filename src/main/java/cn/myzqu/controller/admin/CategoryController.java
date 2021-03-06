package cn.myzqu.controller.admin;

import cn.myzqu.dto.CategoryDTO;
import cn.myzqu.enums.ResultEnum;
import cn.myzqu.service.CategoryService;
import cn.myzqu.utils.ResultVOUtil;
import cn.myzqu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题库类型控制器
 * Created by 的川 on 2018/5/9.
 */
@RestController("AdminCategoryController")
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获得所有可见题库类目
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        List<CategoryDTO> categoryDTOList = categoryService.findAll();
        return ResultVOUtil.success(categoryDTOList);
    }

    /**
     * 新增题库类目
     * @param name 新增的题库类型名称
     * @param parentId 所属父题库类型
     * @return
     */
    @PostMapping("/info")
    public Result add(@RequestParam(value = "name") String name,
                      @RequestParam(value = "parentId",defaultValue = "") Integer parentId){

        if(categoryService.add(name,parentId)) return ResultVOUtil.success();
        else return ResultVOUtil.error(ResultEnum.CATEGORY_CREATE_FAIL);
    }

}
