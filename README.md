# Java_Practice
# java_test - ChatBot server client findout currency 
პატარა სერვერ-კლიენტ აპლიკაცია.
მომხმარებლის დამატება :

    username და ორჯერ ვაფიქსირებინებ password-ს, ბაზაში მომხმარებელი იწერება

ავტორიზაცია:

1.შეყავს username და password ბაზაში იუზერის არსებობის შემთხევაში ვიღებთ იუზერის აიდს და ინფორმაციას იმის შესახებ სტუდენტი არის თუ ლექტორი

    თუ სტუდენტია: შესაბამისი ქეისის მიხედვით ნახულობს -
        თავის score-ს
        ლექტორების ჩამონათვალს

    თუ ლექტორია: შესაბამისი რიქცხვის ქეისის მიხედვით -
        სტუდენტების ჩამონათვალს ნახულობს
        შეუძლია სტუდენტის წაშლა თუ სტუდენტის აიდს შეიყვანს (ჩამონათვალში უწერია აიდები).
        შეუძლია სტუდენტს დაუწეროს ქულა აიდის მიხედვით.

##P.S if is_lecturer == true -> მომხმარებელი ლექტორია else: სტუდენტი შესაძლებელია Runner -ის გამოყენება :)
გამოყენებული postgresql ბრძანებები

create table university_user ( id BIGSERIAL PRIMARY KEY, username varchar(200) NOT NULL UNIQUE, password varchar(200) not null, score bigint default 0, is_lecturer BOOLEAN DEFAULT false, active BOOLEAN DEFAULT true );

-- select * from university_user;

INSERT INTO university_user (username, password, score, active, is_lecturer) VALUES ('THOR', '123', 0, true, false); INSERT INTO university_user (username, password, score, active, is_lecturer) VALUES ('LOKI', '123', 0, true, false); INSERT INTO university_user (username, password, score, active, is_lecturer) VALUES ('HULK', '123', 0, true, true);

SELECT * FROM university_user;

# test_3 - ChatBot server client findout currency 
 from  NBG Bank Currency Service API - http://nbg.gov.ge/currency.wsdl 
# Covid_19  - My API to find out Covid-19 Cases



Nothing special its just little practice, just for fun
