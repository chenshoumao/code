package ldap.entity;

public class Tree
{
  private int id;
  private int pId;
  private String name;
  private String organizationLevel;
  private String address;
  private String postalCode;
  private String mobile;
  private String state;
  private Boolean open;
  private Boolean isParent;

  public Tree()
  {
  }

  public Tree(int id, int pId, String name, String organizationLevel, String address, String postalCode, String mobile, String state, Boolean open, Boolean isParent)
  {
    this.id = id;
    this.pId = pId;
    this.name = name;
    this.organizationLevel = organizationLevel;
    this.address = address;
    this.postalCode = postalCode;
    this.mobile = mobile;
    this.state = state;
    this.open = open;
    this.isParent = isParent;
  }

  public int getId()
  {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getpId() {
    return this.pId;
  }

  public void setpId(int pId) {
    this.pId = pId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getOpen() {
    return this.open;
  }

  public void setOpen(Boolean open) {
    this.open = open;
  }

  public Boolean getIsParent() {
    return this.isParent;
  }

  public void setIsParent(Boolean isParent) {
    this.isParent = isParent;
  }

  public String getOrganizationLevel() {
    return this.organizationLevel;
  }

  public void setOrganizationLevel(String organizationLevel) {
    this.organizationLevel = organizationLevel;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getMobile() {
    return this.mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }
}