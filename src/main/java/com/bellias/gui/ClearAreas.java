package com.bellias.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** The Construction of a class that clears all the input areas according to event.
 * Implements MouseListener Interface.
* @since 31-05-2020
* @version 1.4
* @author it21871 */
public class ClearAreas implements MouseListener {
    
    //=======================================================mouseClicked()=====================================================
    /** The method is implemented so as to catch mouse-clicked event and executes commands to clear all of the input areas.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseClicked(MouseEvent e) {
        GUI.setKind(0);
        GUI.setNameText("");
        GUI.setDayBox("1");
        GUI.setMonthBox("1");
        GUI.setYearBox("2010");
        GUI.setbCityText("");
        GUI.setCitiesText("");
        GUI.setCriteriaText("");
        GUI.setWeatherText("");
        GUI.getSuggestedCityLabel().setVisible(false);
        GUI.getSuggestedCity().setVisible(false);
        GUI.setSuggestedCityText("");
        GUI.getErrorLabel().setVisible(false);
        System.out.println("Areas cleared.");
    }
    //====================================================End of mouseClicked()=================================================
    
    //=======================================================mousePressed()=====================================================
    /** The method is implemented so as to catch mouse-pressed event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mousePressed(MouseEvent e) {

    }
    //====================================================End of mousePressed()=================================================
    
    //=======================================================mouseReleased()====================================================
    /** The method is implemented so as to catch mouse-released event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    //====================================================End of mouseReleased()================================================

    //=======================================================mouseEntered()=====================================================
    /** The method is implemented so as to catch mouse-entered event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    //====================================================End of mouseEntered()=================================================

    //=======================================================mouseExited()======================================================
    /** The method is implemented so as to catch mouse-exited event and executes many commands.
     * Auto-generated method stub.
     * @param e the event which gets caught when happens.
     */
    //==========================================================================================================================
    @Override
    public void mouseExited(MouseEvent e) {

    }
    //====================================================End of mouseExited()==================================================
    
}//=====================================================End of Class ClearAreas =================================================
