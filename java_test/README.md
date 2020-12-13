### პატარა სერვერ-კლიენტ აპლიკაცია. 


### `მომხმარებლის დამატება `:
 1. username და ორჯერ ვაფიქსირებინებ password-ს, ბაზაში მომხმარებელი იწერება

### ```ავტორიზაცია:```

` 1. `შეყავს username და password ბაზაში იუზერის არსებობის შემთხევაში ვიღებთ იუზერის აიდს და ინფორმაციას იმის შესახებ სტუდენტი არის თუ ლექტორი
  
  1. თუ სტუდენტია: 
    შესაბამისი ქეისის მიხედვით ნახულობს  - 
        
        1. თავის score-ს 
        2. ლექტორების ჩამონათვალს
  1. თუ ლექტორია: 
    შესაბამისი რიქცხვის ქეისის მიხედვით - 
        
        1. სტუდენტების ჩამონათვალს ნახულობს
        2. შეუძლია სტუდენტის წაშლა თუ სტუდენტის აიდს შეიყვანს (ჩამონათვალში უწერია აიდები).
        3. შეუძლია სტუდენტს დაუწეროს ქულა აიდის მიხედვით.
  
##P.S 
**`if is_lecturer == true -> მომხმარებელი ლექტორია else: სტუდენტი`**
შესაძლებელია Runner -ის გამოყენება :) 

### **გამოყენებული postgresql ბრძანებები**

create table university_user
(
    id       BIGSERIAL PRIMARY KEY,
    username varchar(200) NOT NULL UNIQUE,
    password varchar(200) not null,
    score bigint default 0,
    is_lecturer BOOLEAN DEFAULT false,
    active   BOOLEAN DEFAULT true
);

-- select * from university_user;

INSERT INTO university_user (username, password, score, active, is_lecturer) VALUES ('THOR', '123', 0, true, false);
INSERT INTO university_user (username, password, score, active, is_lecturer) VALUES ('LOKI', '123', 0, true, false);
INSERT INTO university_user (username, password, score, active, is_lecturer) VALUES ('HULK', '123', 0, true, true);

SELECT * FROM university_user;