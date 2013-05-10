<?xml version="1.0" encoding="UTF-8"?>
<uml:Model xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" xmlns:sysML="http://www.topcased.org/2.0/sysML" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML" xmi:id="idModel" name="upsti">
  <packagedElement xmi:type="uml:Package" xmi:id="idPackage" name="cdcu">
    <ownedRule xmi:type="uml:Constraint" xmi:id="_9sdVMLlaEeK3StCPY1EHaA" name="ContrainteVitesseCible" clientDependency="_gd2QgLlbEeK3StCPY1EHaA">
      <specification xmi:type="uml:LiteralString" xmi:id="_FGSpgLlbEeK3StCPY1EHaA" value="La vitesse cible est inférieure à la vitesse limite."/>
    </ownedRule>
    <packagedElement xmi:type="sysML:Requirement" xmi:id="_u_aoELlREeK3StCPY1EHaA" name="FinalitéReq" clientDependency="_btSEgLlbEeK3StCPY1EHaA _gd2QgLlbEeK3StCPY1EHaA _qdeiYLlbEeK3StCPY1EHaA" isAbstract="true" id="F1" text="Le conducteur connait l'écart entre la vitesse  du véhicule et la vitesse cible."/>
    <packagedElement xmi:type="sysML:Requirement" xmi:id="_8LQXQLlREeK3StCPY1EHaA" name="MissionReq" clientDependency="_Zx7LELlbEeK3StCPY1EHaA" isAbstract="true" id="M1" text="Le conducteur adopte une vitesse régulière"/>
    <packagedElement xmi:type="sysML:Block" xmi:id="_E3H-YLlWEeK3StCPY1EHaA" name="Contexte du système">
      <ownedAttribute xmi:type="uml:Property" xmi:id="_tcpbgLlWEeK3StCPY1EHaA" name="système-à-faire" type="_cv_Q8LlWEeK3StCPY1EHaA" aggregation="composite" association="_tdP4cLlWEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_tcpbgblWEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_tcpbgrlWEeK3StCPY1EHaA" value="1"/>
      </ownedAttribute>
    </packagedElement>
    <packagedElement xmi:type="sysML:Block" xmi:id="_cv_Q8LlWEeK3StCPY1EHaA" name="Système-à-faire" clientDependency="_Ok6yALlZEeK3StCPY1EHaA">
      <ownedAttribute xmi:type="uml:Property" xmi:id="_ahT9cLlXEeK3StCPY1EHaA" name="finalité" type="_MD4DgLlXEeK3StCPY1EHaA" aggregation="composite" association="_ahwCULlXEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_ahT9cblXEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_ahT9crlXEeK3StCPY1EHaA" value="1"/>
      </ownedAttribute>
      <nestedClassifier xmi:type="uml:Activity" xmi:id="_MD4DgLlXEeK3StCPY1EHaA" name="FP1 - Maintenir la connaissance du conducteur de l'écart entre vitesses cible et véhicule">
        <nestedClassifier xmi:type="uml:Activity" xmi:id="_Oa-_oLlpEeK3StCPY1EHaA" name="Régler la vitesse cible"/>
        <nestedClassifier xmi:type="uml:Activity" xmi:id="_bMI98LlpEeK3StCPY1EHaA" name="Acquérir la vitesse véhicule"/>
        <nestedClassifier xmi:type="uml:Activity" xmi:id="_hO808LlpEeK3StCPY1EHaA" name="Informer le conducteur"/>
      </nestedClassifier>
    </packagedElement>
    <packagedElement xmi:type="sysML:Block" xmi:id="_gBL78LlWEeK3StCPY1EHaA" name="Contexte conduite">
      <generalization xmi:type="uml:Generalization" xmi:id="_w9YXULlWEeK3StCPY1EHaA" general="_E3H-YLlWEeK3StCPY1EHaA"/>
      <ownedAttribute xmi:type="uml:Property" xmi:id="_05Y54LlWEeK3StCPY1EHaA" name="utilisateur final" type="_zSzWkLlWEeK3StCPY1EHaA" aggregation="composite" association="_05rNwLlWEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_05Y54blWEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_05Y54rlWEeK3StCPY1EHaA" value="1"/>
      </ownedAttribute>
      <ownedAttribute xmi:type="uml:Property" xmi:id="_kYSSgLlrEeK3StCPY1EHaA" name="véhicule" type="_hGHXILlrEeK3StCPY1EHaA" aggregation="composite" association="_kYsiMLlrEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_kYSSgblrEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_kYSSgrlrEeK3StCPY1EHaA" value="1"/>
      </ownedAttribute>
      <nestedClassifier xmi:type="uml:Activity" xmi:id="_H8ZrQLlsEeK3StCPY1EHaA" name="Avoir une vitesse régulière"/>
    </packagedElement>
    <packagedElement xmi:type="uml:Association" xmi:id="_tdP4cLlWEeK3StCPY1EHaA" name="A_&lt;contexte du système>_&lt;système-à-faire>" memberEnd="_tdP4cblWEeK3StCPY1EHaA _tcpbgLlWEeK3StCPY1EHaA">
      <ownedEnd xmi:type="uml:Property" xmi:id="_tdP4cblWEeK3StCPY1EHaA" name="contexte du système" type="_E3H-YLlWEeK3StCPY1EHaA" association="_tdP4cLlWEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_tdP4crlWEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_tdP4c7lWEeK3StCPY1EHaA" value="1"/>
      </ownedEnd>
    </packagedElement>
    <packagedElement xmi:type="uml:Actor" xmi:id="_zSzWkLlWEeK3StCPY1EHaA" name="Conducteur" clientDependency="_iQqUILlqEeK3StCPY1EHaA _l4n5ULlqEeK3StCPY1EHaA"/>
    <packagedElement xmi:type="uml:Association" xmi:id="_05rNwLlWEeK3StCPY1EHaA" name="A_&lt;contexte conduite>_&lt;conducteur>" memberEnd="_05rNwblWEeK3StCPY1EHaA _05Y54LlWEeK3StCPY1EHaA">
      <ownedEnd xmi:type="uml:Property" xmi:id="_05rNwblWEeK3StCPY1EHaA" name="contexte conduite" type="_gBL78LlWEeK3StCPY1EHaA" association="_05rNwLlWEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_05rNwrlWEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_05rNw7lWEeK3StCPY1EHaA" value="1"/>
      </ownedEnd>
    </packagedElement>
    <packagedElement xmi:type="uml:Association" xmi:id="_ahwCULlXEeK3StCPY1EHaA" name="A_&lt;système-à-faire>_&lt;FP1>" memberEnd="_ahwCUblXEeK3StCPY1EHaA _ahT9cLlXEeK3StCPY1EHaA">
      <ownedEnd xmi:type="uml:Property" xmi:id="_ahwCUblXEeK3StCPY1EHaA" name="système-à-faire" type="_cv_Q8LlWEeK3StCPY1EHaA" association="_ahwCULlXEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_ahwCUrlXEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_ahwCU7lXEeK3StCPY1EHaA" value="1"/>
      </ownedEnd>
    </packagedElement>
    <packagedElement xmi:type="sysML:Block" xmi:id="_EPEYgLlZEeK3StCPY1EHaA" name="Connaissance écart"/>
    <packagedElement xmi:type="uml:Usage" xmi:id="_Ok6yALlZEeK3StCPY1EHaA" name="Usage1" supplier="_EPEYgLlZEeK3StCPY1EHaA" client="_cv_Q8LlWEeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Requirement" xmi:id="_aw9boLlaEeK3StCPY1EHaA" name="ObjectifCarburant" isAbstract="true" id="G1" text="Le conducteur adapte sa conduite pour économiser du carburant."/>
    <packagedElement xmi:type="sysML:Requirement" xmi:id="_hNrncLlaEeK3StCPY1EHaA" name="ObjectifPV" isAbstract="true" id="G2" text="Le conducteur adapte sa conduite pour ne pas avoir de PV."/>
    <packagedElement xmi:type="sysML:Requirement" xmi:id="_uLlBQLlaEeK3StCPY1EHaA" name="ObjectifRespectLimitation" clientDependency="_adYi4LlbEeK3StCPY1EHaA" isAbstract="true" id="G3" text="Le conducteur maintient la vitesse du véhicule en deça de la vitesse limite."/>
    <packagedElement xmi:type="sysML:Refine" xmi:id="_Zx7LELlbEeK3StCPY1EHaA" name="Refine1" supplier="_aw9boLlaEeK3StCPY1EHaA" client="_8LQXQLlREeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Refine" xmi:id="_adYi4LlbEeK3StCPY1EHaA" name="Refine2" supplier="_hNrncLlaEeK3StCPY1EHaA" client="_uLlBQLlaEeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Refine" xmi:id="_btSEgLlbEeK3StCPY1EHaA" name="Refine3" supplier="_8LQXQLlREeK3StCPY1EHaA" client="_u_aoELlREeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Refine" xmi:id="_gd2QgLlbEeK3StCPY1EHaA" name="Refine5" supplier="_uLlBQLlaEeK3StCPY1EHaA" client="_9sdVMLlaEeK3StCPY1EHaA _u_aoELlREeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Refine" xmi:id="_qdeiYLlbEeK3StCPY1EHaA" name="Refine4" supplier="_uLlBQLlaEeK3StCPY1EHaA" client="_u_aoELlREeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Satisfy" xmi:id="_iQqUILlqEeK3StCPY1EHaA" name="Satisfy1" supplier="_8LQXQLlREeK3StCPY1EHaA" client="_zSzWkLlWEeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Satisfy" xmi:id="_l4n5ULlqEeK3StCPY1EHaA" name="Satisfy2" supplier="_uLlBQLlaEeK3StCPY1EHaA" client="_zSzWkLlWEeK3StCPY1EHaA"/>
    <packagedElement xmi:type="sysML:Block" xmi:id="_hGHXILlrEeK3StCPY1EHaA" name="Véhicule"/>
    <packagedElement xmi:type="uml:Association" xmi:id="_kYsiMLlrEeK3StCPY1EHaA" name="A_&lt;contexte conduite>_&lt;véhicule>" memberEnd="_kYsiMblrEeK3StCPY1EHaA _kYSSgLlrEeK3StCPY1EHaA">
      <ownedEnd xmi:type="uml:Property" xmi:id="_kYsiMblrEeK3StCPY1EHaA" name="contexte conduite" type="_gBL78LlWEeK3StCPY1EHaA" association="_kYsiMLlrEeK3StCPY1EHaA">
        <upperValue xmi:type="uml:LiteralUnlimitedNatural" xmi:id="_kYsiMrlrEeK3StCPY1EHaA" value="1"/>
        <lowerValue xmi:type="uml:LiteralInteger" xmi:id="_kYsiM7lrEeK3StCPY1EHaA" value="1"/>
      </ownedEnd>
    </packagedElement>
  </packagedElement>
  <profileApplication xmi:type="uml:ProfileApplication" xmi:id="idProfileApplication">
    <eAnnotations xmi:type="ecore:EAnnotation" xmi:id="idProfileAnnotation" source="http://www.eclipse.org/uml2/2.0.0/UML">
      <references xmi:type="ecore:EPackage" href="pathmap://SysMLActivityExtensionsProfile.uml#ProfileContentId"/>
    </eAnnotations>
    <appliedProfile xmi:type="uml:Profile" href="pathmap://SysMLActivityExtensionsProfile.uml#ActivityProfileId"/>
  </profileApplication>
</uml:Model>
