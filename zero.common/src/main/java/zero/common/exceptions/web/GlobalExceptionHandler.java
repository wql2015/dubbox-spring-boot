package zero.common.exceptions.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zero.common.entity.ResultInfo;

/**
 * 全局controller异常处理
 * @author unique
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 跳转统一异常页面
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    /**
     * 返回json异常信息
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = WebJsonException.class)
    @ResponseBody
    public ResultInfo<String> jsonErrorHandler(HttpServletRequest req, WebJsonException e) throws Exception {
        ResultInfo<String> r = new ResultInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ResultInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
    
}

