package model;

/** Hạng thẻ thành viên. */
public class MembershipTier3 implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String tierName;
    private String description;
    private int pointThreshold;

    public MembershipTier3() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTierName() { return tierName; }
    public void setTierName(String tierName) { this.tierName = tierName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getPointThreshold() { return pointThreshold; }
    public void setPointThreshold(int pointThreshold) { this.pointThreshold = pointThreshold; }
}
