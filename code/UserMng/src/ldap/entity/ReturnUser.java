package ldap.entity;

public class ReturnUser
{
  private String loginName;
  private String password;
  private String curPassword;
  private String newPassword;
  private String confirmPassword;

  public String getLoginName()
  {
    return this.loginName;
  }
  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }
  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getCurPassword() {
    return this.curPassword;
  }
  public void setCurPassword(String curPassword) {
    this.curPassword = curPassword;
  }
  public String getNewPassword() {
    return this.newPassword;
  }
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
  public String getConfirmPassword() {
    return this.confirmPassword;
  }
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
}