package rx.naming.spi;

import javax.naming.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class MemoryContext implements Context {
    private Map<String,Object> lookupMap = new HashMap<>();
    @Override
    public Object lookup(Name name) {
        return lookupMap.get(name.get(0));
    }

    @Override
    public Object lookup(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bind(Name name, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bind(String s, Object o) {
        lookupMap.put(s, o);
    }

    @Override
    public void rebind(Name name, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rebind(String s, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unbind(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unbind(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rename(Name name, Name name1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rename(String s, String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NamingEnumeration<NameClassPair> list(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NamingEnumeration<NameClassPair> list(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NamingEnumeration<Binding> listBindings(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NamingEnumeration<Binding> listBindings(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroySubcontext(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroySubcontext(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Context createSubcontext(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Context createSubcontext(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object lookupLink(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object lookupLink(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NameParser getNameParser(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NameParser getNameParser(String s) {
        return  OneLevelName::new;
    }

    @Override
    public Name composeName(Name name, Name name1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String composeName(String s, String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object addToEnvironment(String s, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object removeFromEnvironment(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Hashtable<?, ?> getEnvironment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
    }

    @Override
    public String getNameInNamespace() {
        throw new UnsupportedOperationException();
    }

    private static class OneLevelName implements Name {
        private final String value;
        public OneLevelName(String value) {
            this.value = value;
        }

        @Override
        public Object clone() {
            throw new UnsupportedOperationException();

        }

        @Override
        public int compareTo(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Enumeration<String> getAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String get(int i) {
            return value;
        }

        @Override
        public Name getPrefix(int i) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Name getSuffix(int i) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean startsWith(Name name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean endsWith(Name name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Name addAll(Name name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Name addAll(int i, Name name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Name add(String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Name add(int i, String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object remove(int i) {
            throw new UnsupportedOperationException();
        }
    }
}
