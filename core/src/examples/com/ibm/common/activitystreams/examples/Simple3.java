package com.ibm.common.activitystreams.examples;

/**
 * The Makers class includes a bunch of static generator 
 * methods that are easiest to use when imported statically
 */
import static com.ibm.common.activitystreams.IO.makeDefaultPrettyPrint;
import static com.ibm.common.activitystreams.Makers.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.ibm.common.activitystreams.Activity;
import com.ibm.common.activitystreams.IO;

/**
 * @author james
 * @version $Revision: 1.0 $
 */
public final class Simple3 {

  // The IO object handles all of the reading and writing of the object
  private static final IO io = makeDefaultPrettyPrint();
  
  private Simple3() {}
  
  /**
   * Method main.
   * @param args String[]
  
   * @throws Exception */
  public static void main(String... args) throws Exception {
    
    // Demonstrates the creation and parsing of a simple Activity Object
    
    // (we'll use this to store the output...)
    ByteArrayOutputStream out = 
      new ByteArrayOutputStream();
    
    // Create the Activity... The API uses a Fluent Generator pattern
    Activity activity = 
      activity()
        .verb("post")
        .actor(
          object()
            .objectType(
              object()
                .id("http://schema.example.net/Person")
                .displayName("Person")
                .alias("person"))
            .id("acct:joe@example.com")
            .displayName("Joe Smith")
        )
        .object(
          object("note")
            .id("http://example.net/posts/1")
            .title(
              nlv()
                .set("en", "This is the title")
                .set("fr", "C'est le titre"))
          )
        .get();
    
    // The Activity object is immutable...
    System.out.println(activity.verb());
    System.out.println(activity.actor());
    System.out.println(activity.object());
    
    // let's write it out to our outputstream
    activity.writeTo(out, io);
    
    // now let's parse it back in
    ByteArrayInputStream in = 
      new ByteArrayInputStream(
        out.toByteArray());
    
    activity = io.readAsActivity(in);
    
    // We get back the same thing...
    System.out.println(activity.verb());
    System.out.println(activity.actor());
    System.out.println(activity.object());
    
    // If you want to see what was serialized, 
    // simply write out to stdout...
    activity.writeTo(System.out, io);
  }
  
}