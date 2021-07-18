/**
 * FileName: MyException
 * Author:   haichaoyang3
 * Date:     2019/6/20 17:24
 * Description: defind exception
 * History:
 */
package com.example.paywhere.commom.exception;

/**
 * Description:〈defind exception〉
 *
 * @author haichaoyang3
 * @create 2019/6/20
 * @since 1.0.0
 */
public class MyException extends RuntimeException {


    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}