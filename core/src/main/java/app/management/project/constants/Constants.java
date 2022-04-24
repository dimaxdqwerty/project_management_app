package app.management.project.constants;

import app.management.project.utils.HWIDUtil;

public class Constants {
    public static final String TICKET_NAME = "ticketName";
    public static final String SHORT_DESCRIPTION = "shortDescription";
    public static final String USERNAMES = "usernames";
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String NO_STATUS = "No status";
    public static final String RESOURCE_PATH = "resourcePath";
    public static final String USERNAME = "username";
    public static final String ROLE = "role";
    public static final String PASSWORD = "password";
    public static final String USER = "user-" + HWIDUtil.getHWID();

    public static final String DEVELOPER_ROLE = "Developer";
    public static final String LEADER_ROLE = "Leader";

    public static final String ASSET_SHARE_COMMONS_PAGE = "asset-share-commons/components/structure/page";
    public static final String ASSET_SHARE_COMMONS_EMPTY_TEMPLATE_DARK =
            "/conf/asset-share-commons/settings/wcm/templates/empty-template-dark";

    public static final String CONTENT_TICKET_STORAGE = "/content/project_management_app/home/ticket-storage";
    public static final String CONTENT_USER = "/content/project_management_app/home/user-profile/user-" + HWIDUtil.getHWID();
    public static final String CONTENT_USER_PROFILE = "/content/project_management_app/home/user-profile";
    public static final String CONTENT_USER_JCR = "/content/project_management_app/home/user-profile/user-" + HWIDUtil.getHWID() + "/jcr:content";
    public static final String CONTENT_HOME = "/content/project_management_app/home";
    public static final String CONTENT_PROJECT_MANAGEMENT_APP = "/content/project_management_app/home/user-profile";
    public static final String CONTENT_ERROR = CONTENT_HOME + "/error";
    public static final String PATH_TO_JCR_CONTENT = "/jcr:content";

    public static final String CQ_PAGE = "cq:Page";
    public static final String CQ_PAGE_CONTENT = "cq:PageContent";
    public static final String CQ_TEMPLATE = "cq:template";
    public static final String JCR_CONTENT = "jcr:content";
    public static final String JCR_TITLE = "jcr:title";
    public static final String PAGE_TITLE = "pageTitle";
    public static final String SLING_RESOURCE_TYPE = "sling:resourceType";

    public static final String COLON = ":";
    public static final String QUESTION_MARK = "?";
    public static final String COLON_WITH_TWO_SLASHES = "://";
    public static final String HTML = ".html";
    public static final String USERNAMES_SPLITTER = ", ";
    public static final String ITERATORS_DIVIDER = " | ";

    public static final String ERROR_PARAM = "error=";

    public static class SQL {
        public static final int USERNAME_COLUMN = 1;
        public static final int PASSWORD_COLUMN = 2;
        public static final int ROLE_COLUMN = 3;

        public static final String SELECT_LOGIN_PASSWORD_USER = "SELECT * FROM users.users WHERE " +
                "username = ? AND password = ?";
        public static final String SELECT_LOGIN_USER = "SELECT * FROM users.users WHERE " +
                "username = ?";
        public static final String INSERT_LOGIN_PASSWORD_USER = "INSERT INTO users.users " +
                "(username, password, role) VALUES (?, ?, ?)";
        public static final String SELECT_USER_ROLE = "SELECT role FROM users.users WHERE " +
                "username = ?";
        public static final String UPDATE_USER_ROLE = "UPDATE users.users SET role = ? WHERE " +
                "username = ?";
        public static final String SELECT_ALL_USERNAMES = "SELECT username FROM users.users";
        public static final String SELECT_ALL_ROLES = "SELECT role FROM users.users";
    }

    public static class ERROR {
        public static final String ACCOUNT_EXISTS = "This account already exists.";
        public static final String WRONG_CREDENTIALS = "Wrong username or password.";
        public static final String CREDENTIALS_LENGTH = "Wrong length of username or password";
    }
}
