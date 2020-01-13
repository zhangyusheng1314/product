package com.zys.product.utils;

import com.zys.product.vo.ResultVO;
import lombok.Data;

@Data
public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(object);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
