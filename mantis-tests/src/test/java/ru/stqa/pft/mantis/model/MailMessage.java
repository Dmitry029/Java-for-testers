package ru.stqa.pft.mantis.model;

/**
 * Created by Администратор on 10.03.2017.
 */
public class MailMessage {
  public String to; //поле - кому письмо
  public String text; //поле - текст письма

  public MailMessage(String to, String text){
    this.to = to;
    this.text  = text;
  }
}


