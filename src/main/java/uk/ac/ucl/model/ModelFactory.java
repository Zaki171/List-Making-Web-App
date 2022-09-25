package uk.ac.ucl.model;


import java.io.IOException;

public class ModelFactory
{
  private static IModel model;

  public static IModel getModel()
  {
    if (model == null)
    {
      model = new Model();
      try{
        model.readFile();
      }catch(IOException e){
        System.out.println("Model failed to load.");
      }
    }
    return model;
  }
}
