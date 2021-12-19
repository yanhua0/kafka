package org.file.test.tes;

/**
 * @author zoujulin [zou.julin@unisinsight.com]
 * @date 2021/01/14 19:49
 * @since 1.0
 */
public enum Category {
    STARTER,
    MAIN_COURSE,
    DESSERT;

    public static Category get(String message) {
        if (message.equals("Vorspeise")) {
            return STARTER;
        } else if ("Hauptspeise".equals(message)) {
            return MAIN_COURSE;
        } else {
            return DESSERT;
        }
    }

    @Override
    public String toString() {
        String message = "";
        switch (this) {
            case STARTER:
                message = "starter";
                break;
            case MAIN_COURSE:
                message = "main course";
                break;
            case DESSERT:
                message = "Dessert";
                break;

        }
        return "Category{" +
                "message='" + message + '\'' +
                '}';
    }
}
