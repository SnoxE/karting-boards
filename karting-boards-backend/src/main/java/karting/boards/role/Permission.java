package karting.boards.role;

public enum Permission {
    TRACK_READ("track_read", Permission.TRACK_RESOURCE);

    public static final String TRACK_RESOURCE = "track";

    private final String label;
    private final String resource;

    Permission(String label, String resource) {
        this.label = label;
        this.resource = resource;
    }

    public String getLabel() { return label; }
    public String getResource() { return resource; }
}
