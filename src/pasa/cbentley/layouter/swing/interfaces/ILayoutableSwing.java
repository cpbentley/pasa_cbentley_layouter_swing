/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.interfaces;

import javax.swing.JComponent;

import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.engine.LayEngineSwing;

/**
 * An {@link ILayoutable} in the Swing world.
 * 
 * It provides access to a single {@link JComponent}.
 * 
 * @author Charles Bentley
 *
 */
public interface ILayoutableSwing extends ILayoutable {

   /**
    * The {@link JComponent} being layouted. 
    * @return should not be null
    */
   public JComponent getComponent();

   public LayEngineSwing getEngineSwing();

}
