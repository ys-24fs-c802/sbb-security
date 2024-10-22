package kr.co.cofile.sbbsecurity.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e, Model model) {
        log.error("User not found: {}", e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorType", "사용자 조회 실패");
        return "error/custom-error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException e, Model model) {
        log.error("Null pointer exception: {}", e.getMessage());
        model.addAttribute("errorMessage", "처리 중 오류가 발생했습니다.");
        model.addAttribute("errorType", "시스템 오류");
        return "error/custom-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        log.error("Unexpected error: {}", e.getMessage());
        model.addAttribute("errorMessage", "예기치 않은 오류가 발생했습니다.");
        model.addAttribute("errorType", "시스템 오류");
        return "error/custom-error";
    }
}