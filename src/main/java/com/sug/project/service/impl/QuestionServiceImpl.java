package com.sug.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sug.project.model.entity.Question;
import com.sug.project.service.QuestionService;
import com.sug.project.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 钱晨
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2026-05-15 15:37:50
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




