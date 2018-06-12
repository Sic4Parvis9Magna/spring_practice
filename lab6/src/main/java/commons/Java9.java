package commons;

import lombok.val;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Java9 {
    static <T> Map<T, T> mapOf(T... ts) {
        assert ts.length % 2 == 0;
        val result = new HashMap<T, T>(ts.length / 2);
        for (int i = 0; i < ts.length;)
            result.put(ts[i++], ts[i++]);
        return Collections.unmodifiableMap(result);
    }
}
