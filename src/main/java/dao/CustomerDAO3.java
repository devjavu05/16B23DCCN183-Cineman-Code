package dao;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import model.Customer3;

/** DAO cho khach hang: kiem tra thong tin dang ki va gui ma OTP. */
public class CustomerDAO3 extends DAO3 {
    private static final Pattern PHONE = Pattern.compile("^0\\d{9}$");
    private static final Pattern EMAIL = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    private static final SecureRandom RND = new SecureRandom();

    /** Thoi gian hieu luc cua OTP (ms). */
    public static final long OTP_TTL_MS = 5 * 60 * 1000L;
    private long otpExpireAt;

    /**
     * Kiem tra thong tin dang ki.
     * @param c        thong tin khach hang (formData) tu form
     * @param password mat khau khach hang nhap
     * @return chuoi rong neu hop le, nguoc lai la noi dung loi dau tien
     */
    public String validateInfo(Customer3 c, String password) {
        if (isBlank(c.getFullName()) || c.getBirthDate() == null || isBlank(c.getPhone())
                || isBlank(c.getGender()) || isBlank(c.getEmail()) || isBlank(password)) {
            return "Vui lòng nhập đầy đủ thông tin.";
        }
        if (!PHONE.matcher(c.getPhone().trim()).matches()) {
            return "Số điện thoại không hợp lệ (10 chữ số, bắt đầu bằng 0).";
        }
        if (!EMAIL.matcher(c.getEmail().trim()).matches()) {
            return "Email không hợp lệ.";
        }
        if (c.getBirthDate().after(new Date(System.currentTimeMillis()))) {
            return "Ngày sinh không được lớn hơn ngày hiện tại.";
        }
        if (password.length() < 8) {
            return "Mật khẩu phải có ít nhất 8 ký tự.";
        }
        if (existsPhoneOrEmail(c.getPhone().trim(), c.getEmail().trim())) {
            return "Số điện thoại/Email đã được đăng kí.";
        }
        return "";
    }

    /** Truy van tblCustomer3 xem so dien thoai hoac email da ton tai chua. */
    public boolean existsPhoneOrEmail(String phone, String email) {
        String sql = "SELECT COUNT(*) FROM tblCustomer3 WHERE phone = ? OR email = ?";
        try (PreparedStatement ps = conn().prepareStatement(sql)) {
            ps.setString(1, phone);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            throw new IllegalStateException("Loi truy van CSDL: " + e.getMessage(), e);
        }
    }

    /**
     * Sinh ma OTP 6 chu so va "gui" toi email/so dien thoai khach hang.
     * Ban demo: ma duoc ghi ra log may chu; he thong that goi cong Email/SMS (xem so do trien khai).
     * Ma OTP tra ve; thoi diem het han cua ma do doc qua getOtpExpireAt().
     * @return ma OTP da sinh (de trang OtpPage3 doi chieu)
     */
    public String sendOtp(String phoneOrEmail) {
        String otp = String.format("%06d", RND.nextInt(1_000_000));
        otpExpireAt = System.currentTimeMillis() + OTP_TTL_MS; // thoi diem het han cua ma vua gui
        System.out.println("[CineMan3][OTP demo] gui ma " + otp + " toi " + phoneOrEmail);
        return otp;
    }

    /** Thoi diem (ms) het han cua ma OTP gui gan nhat boi doi tuong nay. */
    public long getOtpExpireAt() {
        return otpExpireAt;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
