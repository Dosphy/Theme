package com.work.controller;

import com.work.domain.pojo.Option;
import com.work.domain.pojo.Question;
import com.work.domain.pojo.QuestionCategory;
import com.work.domain.pojo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.work.service.OptionService;
import com.work.service.QuestionCategoryService;
import com.work.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private OptionService optionService;
    
    @Autowired
    private QuestionCategoryService questionCategoryService;
    
    /**
     * 获取题目分类列表
     */
    @GetMapping("/categories")
    public Result getCategories() {
        List<QuestionCategory> categories = questionCategoryService.list();
        
        // 为每个分类计算题目数量
        for (QuestionCategory category : categories) {
            // 使用MyBatis-Plus的条件构造器统计该分类下的题目数量
            LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Question::getCategoryId, category.getId());
            long count = questionService.count(wrapper);
            category.setQuestionCount((int) count);
        }
        
        return Result.ok(categories);
    }
    
    /**
     * 获取题目列表（支持分页）
     */
    @GetMapping("/list")
    public Result getQuestions(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer size,
                              @RequestParam(required = false) Integer type,
                              @RequestParam(required = false) Integer difficulty,
                              @RequestParam(required = false) Long categoryId) {
        // 根据categoryId筛选题目
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            wrapper.eq(Question::getCategoryId, categoryId);
        }
        
        // 获取题目列表
        List<Question> questions = questionService.list(wrapper);
        
        // 为每个题目获取选项
        for (Question question : questions) {
            List<Option> options = optionService.getOptionsByQuestionId(question.getId());
            question.setOptions(options);
        }
        
        // 返回前端期望的格式
        Map<String, Object> responseData = Map.of(
                "questions", questions,
                "categoryName", "题库"
        );
        
        return Result.ok(responseData);
    }
    
    /**
     * 获取题目详情（包含选项）
     */
    @GetMapping("/detail/{id}")
    public Result getQuestionDetail(@PathVariable Long id) {
        Question question = questionService.getById(id);
        if (question == null) {
            return Result.fail("题目不存在");
        }
        
        List<Option> options = optionService.getOptionsByQuestionId(id);
        
        Map<String, Object> result = Map.of(
                "question", question,
                "options", options
        );
        
        return Result.ok(result);
    }
    
    /**
     * 创建题目
     */
    @PostMapping("/create")
    public Result createQuestion(@RequestBody Map<String, Object> request) {
        // 这里可以根据需要实现复杂的创建逻辑
        return Result.ok("题目创建成功");
    }
    
    /**
     * 更新题目
     */
    @PutMapping("/update/{id}")
    public Result updateQuestion(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        // 这里可以根据需要实现复杂的更新逻辑
        return Result.ok("题目更新成功");
    }
    
    /**
     * 删除题目
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteQuestion(@PathVariable Long id) {
        boolean success = questionService.removeById(id);
        if (success) {
            return Result.ok("题目删除成功");
        } else {
            return Result.fail("题目删除失败");
        }
    }
}