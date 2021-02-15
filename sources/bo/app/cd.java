package bo.app;

import com.appboy.models.IPutIntoJson;
import java.util.UUID;

public final class cd implements IPutIntoJson<String> {
    private final UUID a;
    private final String b;

    public cd(UUID uuid) {
        this.a = uuid;
        this.b = uuid.toString();
    }

    public static cd a() {
        return new cd(UUID.randomUUID());
    }

    public static cd a(String str) {
        return new cd(UUID.fromString(str));
    }

    public String toString() {
        return this.b;
    }

    /* renamed from: b */
    public String forJsonPut() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((cd) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
