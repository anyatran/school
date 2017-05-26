/*
 things to compute:
 1) count of runners younger than 40
 2) count of all women
 3) count og all finishes in under 6 h
 4) count of 1-3
 =====================
 1) find all runners younger than 40
 2) find all women runners
 3) find all runners who finished in under 6 h
 4) combos
 =======================
 1) r there any runners younger than 40
 2) r there any  women runners
3) r there any runners finished is under 60
 */

/*

                    ILoR
          MtLoR               ConsLoR
          ()               Runner first
                           ILoR rest



Runner
String name
String gender
int time
int age
int bib
 */


/*
 * countWomen()
for MtLoR:
0;


for ConsLoR
if (this.first.gender.equals("women"))
1 + this.rest.countWomen();
else
restur this.rest.countWomen();'


findWomen:
for MtLoR:
return this;

for ConsLoR:
if (this.first.gender.equals("women"){
return new ConsLoS(this.first.name, this.rest.findWomen());}
else {
return this.rest.findWomen())


// in consLoR

int count(...) {
    if (...){
        return 1 + this.rest.count(...);
    }
    else {
        return this.rest.count(...);
    }
}



// in MtLoR
int count(...){
    return 0;
}



 */
interface IPred { 
    boolean check(Runner r);
}


class under40 implements IPred {
    // runner is a parameter
    public boolean check(Runner r){
        return r.age < 40;
    }
}
class women implements IPred {
    public boolean check(Runner r){
        return r.gender.equals("women");
    }
}



//in consLoR

int count(IPred pred) {
    if (pred.check(this.first){
        return 1 + this.rest.count(pred);
    }
    else {
        return this.rest.count(ored);
    }
}



//in MtLoR
int count(IPred p){
    return 0;
}



// examples:
class Examples{
    Runner r1 = new Runner("jen", "f", 20000, 45, 6);
    Runner r2 = new Runner("d2", "m", 45000, 47, 1984);
    Runner r3 = new Runner("tarzan", "m", 5, 50, 450);

    IoR l1 = new ConsLoR(r1, new ConsLoR(r2, new ConsLoR(r3 , new MtLoR())));

    //makethoners count(new IsWomen()) => r1
    //makethoenrs count(new Under40()) => 0
    //marathoners.count (new andPred(new isWomen(), new Under40())) => 0
}


// in ConLoR
ILoR find(IPred pred){
    if (pred.check(this.first)){
        return new ConLoR(this.first, thi. redt.find(pred));
    }
    else return this.rest.find(pred);
}


// in MT'
ILoR find(IPred pred){
    return this;
}

class andPred implements IPred{
    IPred one, two
    andPred(Ipred first, IPred second) {
        this.one = first;
        this.two = second;
    }


    public boolean check(Runner r) {
        return this.one.check(r) && this.two.check(r);
    }


}

class UnderMaxAge ipmlement IPred{
    int maxAge;
    UnderMaxAge(int a){
        this.maxAge = a;
    }
    public boolean check(Runner r){
        retunr r.age < this.maxAge;
    }
}







