/*
*/

interface Dict<K, V> {
    V get(K k);
    void set(K key, V val);
    boolean contains(K key);
    int size();
}

ArrayList<T> implements Dict<Integer, T>

IList<Pair<K, V>> implements Dict<K, V>;


hash(String n) = last letter mod 11
"Ben" => "n" => 15 => 4
viera => 0
office => 5
fundies => 8
aslissa => 0

0(v)  1  2  3  4(b)  5(o)  6  7  8(f)  9  10  11
 (a)



hashCode are distinct => hashes distinct
hashCode are equal => hashes are equal
alissa and viera are not equal but have the same code
class Object{
    boolean equals(Oject s);
    int hashCode();
}

public boolean equals(Object other){
    if(other.instanceOf Book){
        return false;
    }
    Book beother = (Book) other;
    return (this.name.equals(beOther.name) &&
            this.price == beOther.price);
}


publilc int hashCode(){
    return this.name.hashCode() + this.price;
}













     