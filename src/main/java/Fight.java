public class Fight {
    public void initiate(Humans h, Goblins g){
        if (h.getX_axis() == g.getX_axis() || h.getY_axis() == g.getY_axis()){
            System.out.println("fight started");
        }
    }
}
