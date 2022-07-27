package org.sqli.pfe.enset.utils.dtos;

import lombok.Builder;
import lombok.Getter;
import org.sqli.pfe.enset.utils.common.CommonUtils;

@Builder
@Getter
public class SearchParamDto {
     private String thread;
     private String login;

     public boolean isEmpty(){
          return !isFull();
     }

     public boolean isFull() {
          return isLoginResigned() && isThreadResigned();
     }

     public boolean isThreadResigned() {
          return CommonUtils.isNotBlank(thread);
     }
     public boolean isLoginResigned() {
          return CommonUtils.isNotBlank(login);
     }
}
