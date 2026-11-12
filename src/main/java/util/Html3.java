package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/** Ham tien ich cho cac trang JSP (khong thuoc thiet ke nghiep vu): escape HTML, dinh dang tien. */
public final class Html3 {
    private Html3() { }

    public static String esc(Object o) {
        if (o == null) return "";
        return String.valueOf(o).replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    public static String money(double v) {
        DecimalFormat f = new DecimalFormat("#,##0", DecimalFormatSymbols.getInstance(new Locale("vi", "VN")));
        return f.format(v) + " đ";
    }

    public static String code(String prefix, int id) {
        return String.format("%s%03d", prefix, id);
    }
}
