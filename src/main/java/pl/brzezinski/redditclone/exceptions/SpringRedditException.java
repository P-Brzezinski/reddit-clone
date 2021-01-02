package pl.brzezinski.redditclone.exceptions;

public class SpringRedditException extends RuntimeException{

    public SpringRedditException(String exMessage){
        super(exMessage);
    }
}
