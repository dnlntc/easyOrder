public class TestServer
{
    public static void main(String[] args) {
        // starting point to test the server
        if (args.length!=1) {
            System.out.println("Error, usage is: java TestServer <port>");
            System.exit(-1);
        }
        System.out.println("Starting server on port "+args[0]);

        int port = Integer.parseInt(args[0]);
        KitchenServer my_server = new KitchenServer(port);

    }
}
