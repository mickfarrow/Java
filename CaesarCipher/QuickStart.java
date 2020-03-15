class QuickStart {
    private static CaesarCipher cipher;
    public static void main(String[] args) {

        cipher = new CaesarCipher(3);

        String input = "My Name is Michael Caine";

        DoIt(input);

        input = "MY NAME IS MICHAEL CAINE";
        DoIt(input);

    }
    private static void DoIt(String input){

        String s = cipher.encrypt(input);
        String r = cipher.decrypt(s);
        System.out.println(input + " > " + s);
        System.out.println(s + " > " + r);

    }
}