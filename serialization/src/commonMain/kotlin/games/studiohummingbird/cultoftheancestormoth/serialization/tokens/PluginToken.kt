/**
Cult of the Ancestor Moth (PluginToken.kt)
Copyright (C) 2025  Zymus (moore.zyle@gmail.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
@file:OptIn(InternalSerializationApi::class)

package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.AACTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ACHRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ACTISerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ADDNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ALCHSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.AMMOSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ANIOSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.APPASerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ARMASerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ARMOSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ARTOSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ASPCSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ASTPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.AVIFSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.BOOKSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.BPTDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CAMSSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CELLSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CLASSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CLDCSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CLFMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CLMTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.COBJSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.COLLSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CONTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CPTHSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.CSTYSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DEBRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DIALSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DLBRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DLVWSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DOBJSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DOORSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.DUALSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ECZNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.EFSHSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.ENCHSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.EQUPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.EXPLSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.EYESSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.FACTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.FLORSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.FLSTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.FSTPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.FSTSSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.FURNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.GLOBSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.GMSTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.GRASSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.GRUPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.HAIRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.HAZDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.HDPTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.IDLESerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.IDLMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.IMADSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.IMGSSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.INFOSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.INGRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.IPCTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.IPDSSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.KEYMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.KYWDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LANDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LCRTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LCTNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LGTMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LIGHSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LSCRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LTEXSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LVLISerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LVLNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.LVSPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MATOSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MATTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MESGSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MGEFSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MISCSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MOVTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MSTTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MUSCSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.MUSTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.NAVISerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.NAVMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.NPCSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.OTFTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.PACKSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.PERKSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.PGRESerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.PHZDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.PROJSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.PWATSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.QUSTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.RACESerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.REFRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.REGNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.RELASerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.REVBSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.RFCTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.RGDLSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SCENSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SCOLSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SCPTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SCRLSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SHOUSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SLGMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SMBNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SMENSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SMQNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SNCTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SNDRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SOPMSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SOUNSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SPELSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.SPGDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.STATSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.TACTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.TES4Serializer
import games.studiohummingbird.cultoftheancestormoth.serialization.TREESerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.TXSTSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.VTYPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.WATRSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.WEAPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.WOOPSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.WRLDSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.WTHRSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KeepGeneratedSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface PluginToken

sealed interface PluginRecord : PluginToken {
   val header: RecordHeader
   val fields: RecordValue
}

@KeepGeneratedSerializer
@Serializable(TES4Serializer::class)
@SerialName(TES4.SERIAL_NAME)
data class TES4(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "TES4"
    }
}


@KeepGeneratedSerializer
@Serializable(GRUPSerializer::class)
@SerialName(GRUP.SERIAL_NAME)
data class GRUP(
    val header: GroupHeader,
    val children: List<PluginToken>
) : PluginToken {
    companion object {
        const val SERIAL_NAME = "GRUP"
    }
}


@KeepGeneratedSerializer
@Serializable(GMSTSerializer::class)
@SerialName(GMST.SERIAL_NAME)
data class GMST(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "GMST"
    }
}


@KeepGeneratedSerializer
@Serializable(KYWDSerializer::class)
@SerialName(KYWD.SERIAL_NAME)
data class KYWD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "KYWD"
    }
}


@KeepGeneratedSerializer
@Serializable(LCRTSerializer::class)
@SerialName(LCRT.SERIAL_NAME)
data class LCRT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LCRT"
    }
}


@KeepGeneratedSerializer
@Serializable(AACTSerializer::class)
@SerialName(AACT.SERIAL_NAME)
data class AACT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "AACT"
    }
}


@KeepGeneratedSerializer
@Serializable(TXSTSerializer::class)
@SerialName(TXST.SERIAL_NAME)
data class TXST(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "TXST"
    }
}


@KeepGeneratedSerializer
@Serializable(GLOBSerializer::class)
@SerialName(GLOB.SERIAL_NAME)
data class GLOB(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "GLOB"
    }
}


@KeepGeneratedSerializer
@Serializable(CLASSerializer::class)
@SerialName(CLAS.SERIAL_NAME)
data class CLAS(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CLAS"
    }
}


@KeepGeneratedSerializer
@Serializable(FACTSerializer::class)
@SerialName(FACT.SERIAL_NAME)
data class FACT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "FACT"
    }
}


@KeepGeneratedSerializer
@Serializable(HDPTSerializer::class)
@SerialName(HDPT.SERIAL_NAME)
data class HDPT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "HDPT"
    }
}


@KeepGeneratedSerializer
@Serializable(HAIRSerializer::class)
@SerialName(HAIR.SERIAL_NAME)
data class HAIR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "HAIR"
    }
}


@KeepGeneratedSerializer
@Serializable(EYESSerializer::class)
@SerialName(EYES.SERIAL_NAME)
data class EYES(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "EYES"
    }
}


@KeepGeneratedSerializer
@Serializable(RACESerializer::class)
@SerialName(RACE.SERIAL_NAME)
data class RACE(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "RACE"
    }
}


@KeepGeneratedSerializer
@Serializable(SOUNSerializer::class)
@SerialName(SOUN.SERIAL_NAME)
data class SOUN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SOUN"
    }
}


@KeepGeneratedSerializer
@Serializable(ASPCSerializer::class)
@SerialName(ASPC.SERIAL_NAME)
data class ASPC(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ASPC"
    }
}


@KeepGeneratedSerializer
@Serializable(MGEFSerializer::class)
@SerialName(MGEF.SERIAL_NAME)
data class MGEF(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MGEF"
    }
}


@KeepGeneratedSerializer
@Serializable(SCPTSerializer::class)
@SerialName(SCPT.SERIAL_NAME)
data class SCPT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SCPT"
    }
}


@KeepGeneratedSerializer
@Serializable(LTEXSerializer::class)
@SerialName(LTEX.SERIAL_NAME)
data class LTEX(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LTEX"
    }
}


@KeepGeneratedSerializer
@Serializable(ENCHSerializer::class)
@SerialName(ENCH.SERIAL_NAME)
data class ENCH(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ENCH"
    }
}


@KeepGeneratedSerializer
@Serializable(SPELSerializer::class)
@SerialName(SPEL.SERIAL_NAME)
data class SPEL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SPEL"
    }
}


@KeepGeneratedSerializer
@Serializable(SCRLSerializer::class)
@SerialName(SCRL.SERIAL_NAME)
data class SCRL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SCRL"
    }
}


@KeepGeneratedSerializer
@Serializable(ACTISerializer::class)
@SerialName(ACTI.SERIAL_NAME)
data class ACTI(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ACTI"
    }
}


@KeepGeneratedSerializer
@Serializable(TACTSerializer::class)
@SerialName(TACT.SERIAL_NAME)
data class TACT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "TACT"
    }
}


@KeepGeneratedSerializer
@Serializable(ARMOSerializer::class)
@SerialName(ARMO.SERIAL_NAME)
data class ARMO(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ARMO"
    }
}


@KeepGeneratedSerializer
@Serializable(BOOKSerializer::class)
@SerialName(BOOK.SERIAL_NAME)
data class BOOK(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "BOOK"
    }
}


@KeepGeneratedSerializer
@Serializable(CONTSerializer::class)
@SerialName(CONT.SERIAL_NAME)
data class CONT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CONT"
    }
}


@KeepGeneratedSerializer
@Serializable(DOORSerializer::class)
@SerialName(DOOR.SERIAL_NAME)
data class DOOR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DOOR"
    }
}


@KeepGeneratedSerializer
@Serializable(INGRSerializer::class)
@SerialName(INGR.SERIAL_NAME)
data class INGR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "INGR"
    }
}


@KeepGeneratedSerializer
@Serializable(LIGHSerializer::class)
@SerialName(LIGH.SERIAL_NAME)
data class LIGH(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LIGH"
    }
}


@KeepGeneratedSerializer
@Serializable(MISCSerializer::class)
@SerialName(MISC.SERIAL_NAME)
data class MISC(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MISC"
    }
}


@KeepGeneratedSerializer
@Serializable(APPASerializer::class)
@SerialName(APPA.SERIAL_NAME)
data class APPA(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "APPA"
    }
}


@KeepGeneratedSerializer
@Serializable(STATSerializer::class)
@SerialName(STAT.SERIAL_NAME)
data class STAT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "STAT"
    }
}


@KeepGeneratedSerializer
@Serializable(SCOLSerializer::class)
@SerialName(SCOL.SERIAL_NAME)
data class SCOL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SCOL"
    }
}


@KeepGeneratedSerializer
@Serializable(MSTTSerializer::class)
@SerialName(MSTT.SERIAL_NAME)
data class MSTT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MSTT"
    }
}


@KeepGeneratedSerializer
@Serializable(PWATSerializer::class)
@SerialName(PWAT.SERIAL_NAME)
data class PWAT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "PWAT"
    }
}


@KeepGeneratedSerializer
@Serializable(GRASSerializer::class)
@SerialName(GRAS.SERIAL_NAME)
data class GRAS(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "GRAS"
    }
}


@KeepGeneratedSerializer
@Serializable(TREESerializer::class)
@SerialName(TREE.SERIAL_NAME)
data class TREE(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "TREE"
    }
}


@KeepGeneratedSerializer
@Serializable(CLDCSerializer::class)
@SerialName(CLDC.SERIAL_NAME)
data class CLDC(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CLDC"
    }
}


@KeepGeneratedSerializer
@Serializable(FLORSerializer::class)
@SerialName(FLOR.SERIAL_NAME)
data class FLOR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "FLOR"
    }
}


@KeepGeneratedSerializer
@Serializable(FURNSerializer::class)
@SerialName(FURN.SERIAL_NAME)
data class FURN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "FURN"
    }
}


@KeepGeneratedSerializer
@Serializable(WEAPSerializer::class)
@SerialName(WEAP.SERIAL_NAME)
data class WEAP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "WEAP"
    }
}


@KeepGeneratedSerializer
@Serializable(AMMOSerializer::class)
@SerialName(AMMO.SERIAL_NAME)
data class AMMO(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "AMMO"
    }
}


@KeepGeneratedSerializer
@Serializable(NPCSerializer::class)
@SerialName(NPC.SERIAL_NAME)
data class NPC(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "NPC_"
    }
}


@KeepGeneratedSerializer
@Serializable(LVLNSerializer::class)
@SerialName(LVLN.SERIAL_NAME)
data class LVLN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LVLN"
    }
}


@KeepGeneratedSerializer
@Serializable(KEYMSerializer::class)
@SerialName(KEYM.SERIAL_NAME)
data class KEYM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "KEYM"
    }
}


@KeepGeneratedSerializer
@Serializable(ALCHSerializer::class)
@SerialName(ALCH.SERIAL_NAME)
data class ALCH(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ALCH"
    }
}


@KeepGeneratedSerializer
@Serializable(IDLMSerializer::class)
@SerialName(IDLM.SERIAL_NAME)
data class IDLM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "IDLM"
    }
}


@KeepGeneratedSerializer
@Serializable(COBJSerializer::class)
@SerialName(COBJ.SERIAL_NAME)
data class COBJ(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "COBJ"
    }
}


@KeepGeneratedSerializer
@Serializable(PROJSerializer::class)
@SerialName(PROJ.SERIAL_NAME)
data class PROJ(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "PROJ"
    }
}


@KeepGeneratedSerializer
@Serializable(HAZDSerializer::class)
@SerialName(HAZD.SERIAL_NAME)
data class HAZD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "HAZD"
    }
}


@KeepGeneratedSerializer
@Serializable(SLGMSerializer::class)
@SerialName(SLGM.SERIAL_NAME)
data class SLGM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SLGM"
    }
}


@KeepGeneratedSerializer
@Serializable(LVLISerializer::class)
@SerialName(LVLI.SERIAL_NAME)
data class LVLI(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LVLI"
    }
}


@KeepGeneratedSerializer
@Serializable(WTHRSerializer::class)
@SerialName(WTHR.SERIAL_NAME)
data class WTHR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "WTHR"
    }
}


@KeepGeneratedSerializer
@Serializable(CLMTSerializer::class)
@SerialName(CLMT.SERIAL_NAME)
data class CLMT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CLMT"
    }
}


@KeepGeneratedSerializer
@Serializable(SPGDSerializer::class)
@SerialName(SPGD.SERIAL_NAME)
data class SPGD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SPGD"
    }
}


@KeepGeneratedSerializer
@Serializable(RFCTSerializer::class)
@SerialName(RFCT.SERIAL_NAME)
data class RFCT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "RFCT"
    }
}


@KeepGeneratedSerializer
@Serializable(REGNSerializer::class)
@SerialName(REGN.SERIAL_NAME)
data class REGN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "REGN"
    }
}


@KeepGeneratedSerializer
@Serializable(NAVISerializer::class)
@SerialName(NAVI.SERIAL_NAME)
data class NAVI(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "NAVI"
    }
}


@KeepGeneratedSerializer
@Serializable(CELLSerializer::class)
@SerialName(CELL.SERIAL_NAME)
data class CELL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CELL"
    }
}


@KeepGeneratedSerializer
@Serializable(WRLDSerializer::class)
@SerialName(WRLD.SERIAL_NAME)
data class WRLD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "WRLD"
    }
}


@KeepGeneratedSerializer
@Serializable(DIALSerializer::class)
@SerialName(DIAL.SERIAL_NAME)
data class DIAL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DIAL"
    }
}


@KeepGeneratedSerializer
@Serializable(QUSTSerializer::class)
@SerialName(QUST.SERIAL_NAME)
data class QUST(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "QUST"
    }
}


@KeepGeneratedSerializer
@Serializable(IDLESerializer::class)
@SerialName(IDLE.SERIAL_NAME)
data class IDLE(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "IDLE"
    }
}


@KeepGeneratedSerializer
@Serializable(PACKSerializer::class)
@SerialName(PACK.SERIAL_NAME)
data class PACK(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "PACK"
    }
}


@KeepGeneratedSerializer
@Serializable(CSTYSerializer::class)
@SerialName(CSTY.SERIAL_NAME)
data class CSTY(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CSTY"
    }
}


@KeepGeneratedSerializer
@Serializable(LSCRSerializer::class)
@SerialName(LSCR.SERIAL_NAME)
data class LSCR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LSCR"
    }
}


@KeepGeneratedSerializer
@Serializable(LVSPSerializer::class)
@SerialName(LVSP.SERIAL_NAME)
data class LVSP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LVSP"
    }
}


@KeepGeneratedSerializer
@Serializable(ANIOSerializer::class)
@SerialName(ANIO.SERIAL_NAME)
data class ANIO(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ANIO"
    }
}


@KeepGeneratedSerializer
@Serializable(WATRSerializer::class)
@SerialName(WATR.SERIAL_NAME)
data class WATR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "WATR"
    }
}


@KeepGeneratedSerializer
@Serializable(EFSHSerializer::class)
@SerialName(EFSH.SERIAL_NAME)
data class EFSH(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "EFSH"
    }
}


@KeepGeneratedSerializer
@Serializable(EXPLSerializer::class)
@SerialName(EXPL.SERIAL_NAME)
data class EXPL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "EXPL"
    }
}


@KeepGeneratedSerializer
@Serializable(DEBRSerializer::class)
@SerialName(DEBR.SERIAL_NAME)
data class DEBR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DEBR"
    }
}


@KeepGeneratedSerializer
@Serializable(IMGSSerializer::class)
@SerialName(IMGS.SERIAL_NAME)
data class IMGS(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "IMGS"
    }
}


@KeepGeneratedSerializer
@Serializable(IMADSerializer::class)
@SerialName(IMAD.SERIAL_NAME)
data class IMAD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "IMAD"
    }
}


@KeepGeneratedSerializer
@Serializable(FLSTSerializer::class)
@SerialName(FLST.SERIAL_NAME)
data class FLST(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "FLST"
    }
}


@KeepGeneratedSerializer
@Serializable(PERKSerializer::class)
@SerialName(PERK.SERIAL_NAME)
data class PERK(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "PERK"
    }
}


@KeepGeneratedSerializer
@Serializable(BPTDSerializer::class)
@SerialName(BPTD.SERIAL_NAME)
data class BPTD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "BPTD"
    }
}


@KeepGeneratedSerializer
@Serializable(ADDNSerializer::class)
@SerialName(ADDN.SERIAL_NAME)
data class ADDN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ADDN"
    }
}


@KeepGeneratedSerializer
@Serializable(AVIFSerializer::class)
@SerialName(AVIF.SERIAL_NAME)
data class AVIF(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "AVIF"
    }
}


@KeepGeneratedSerializer
@Serializable(CAMSSerializer::class)
@SerialName(CAMS.SERIAL_NAME)
data class CAMS(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CAMS"
    }
}


@KeepGeneratedSerializer
@Serializable(CPTHSerializer::class)
@SerialName(CPTH.SERIAL_NAME)
data class CPTH(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CPTH"
    }
}


@KeepGeneratedSerializer
@Serializable(VTYPSerializer::class)
@SerialName(VTYP.SERIAL_NAME)
data class VTYP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "VTYP"
    }
}


@KeepGeneratedSerializer
@Serializable(MATTSerializer::class)
@SerialName(MATT.SERIAL_NAME)
data class MATT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MATT"
    }
}


@KeepGeneratedSerializer
@Serializable(IPCTSerializer::class)
@SerialName(IPCT.SERIAL_NAME)
data class IPCT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "IPCT"
    }
}


@KeepGeneratedSerializer
@Serializable(IPDSSerializer::class)
@SerialName(IPDS.SERIAL_NAME)
data class IPDS(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "IPDS"
    }
}


@KeepGeneratedSerializer
@Serializable(ARMASerializer::class)
@SerialName(ARMA.SERIAL_NAME)
data class ARMA(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ARMA"
    }
}


@KeepGeneratedSerializer
@Serializable(ECZNSerializer::class)
@SerialName(ECZN.SERIAL_NAME)
data class ECZN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ECZN"
    }
}


@KeepGeneratedSerializer
@Serializable(LCTNSerializer::class)
@SerialName(LCTN.SERIAL_NAME)
data class LCTN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LCTN"
    }
}


@KeepGeneratedSerializer
@Serializable(MESGSerializer::class)
@SerialName(MESG.SERIAL_NAME)
data class MESG(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MESG"
    }
}


@KeepGeneratedSerializer
@Serializable(RGDLSerializer::class)
@SerialName(RGDL.SERIAL_NAME)
data class RGDL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "RGDL"
    }
}


@KeepGeneratedSerializer
@Serializable(DOBJSerializer::class)
@SerialName(DOBJ.SERIAL_NAME)
data class DOBJ(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DOBJ"
    }
}


@KeepGeneratedSerializer
@Serializable(LGTMSerializer::class)
@SerialName(LGTM.SERIAL_NAME)
data class LGTM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LGTM"
    }
}


@KeepGeneratedSerializer
@Serializable(MUSCSerializer::class)
@SerialName(MUSC.SERIAL_NAME)
data class MUSC(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MUSC"
    }
}


@KeepGeneratedSerializer
@Serializable(FSTPSerializer::class)
@SerialName(FSTP.SERIAL_NAME)
data class FSTP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "FSTP"
    }
}


@KeepGeneratedSerializer
@Serializable(FSTSSerializer::class)
@SerialName(FSTS.SERIAL_NAME)
data class FSTS(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "FSTS"
    }
}


@KeepGeneratedSerializer
@Serializable(SMBNSerializer::class)
@SerialName(SMBN.SERIAL_NAME)
data class SMBN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SMBN"
    }
}


@KeepGeneratedSerializer
@Serializable(SMQNSerializer::class)
@SerialName(SMQN.SERIAL_NAME)
data class SMQN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SMQN"
    }
}


@KeepGeneratedSerializer
@Serializable(SMENSerializer::class)
@SerialName(SMEN.SERIAL_NAME)
data class SMEN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SMEN"
    }
}


@KeepGeneratedSerializer
@Serializable(DLBRSerializer::class)
@SerialName(DLBR.SERIAL_NAME)
data class DLBR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DLBR"
    }
}


@KeepGeneratedSerializer
@Serializable(MUSTSerializer::class)
@SerialName(MUST.SERIAL_NAME)
data class MUST(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MUST"
    }
}


@KeepGeneratedSerializer
@Serializable(DLVWSerializer::class)
@SerialName(DLVW.SERIAL_NAME)
data class DLVW(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DLVW"
    }
}


@KeepGeneratedSerializer
@Serializable(WOOPSerializer::class)
@SerialName(WOOP.SERIAL_NAME)
data class WOOP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "WOOP"
    }
}


@KeepGeneratedSerializer
@Serializable(SHOUSerializer::class)
@SerialName(SHOU.SERIAL_NAME)
data class SHOU(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SHOU"
    }
}


@KeepGeneratedSerializer
@Serializable(EQUPSerializer::class)
@SerialName(EQUP.SERIAL_NAME)
data class EQUP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "EQUP"
    }
}


@KeepGeneratedSerializer
@Serializable(RELASerializer::class)
@SerialName(RELA.SERIAL_NAME)
data class RELA(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "RELA"
    }
}


@KeepGeneratedSerializer
@Serializable(SCENSerializer::class)
@SerialName(SCEN.SERIAL_NAME)
data class SCEN(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SCEN"
    }
}


@KeepGeneratedSerializer
@Serializable(ASTPSerializer::class)
@SerialName(ASTP.SERIAL_NAME)
data class ASTP(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ASTP"
    }
}


@KeepGeneratedSerializer
@Serializable(OTFTSerializer::class)
@SerialName(OTFT.SERIAL_NAME)
data class OTFT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "OTFT"
    }
}


@KeepGeneratedSerializer
@Serializable(ARTOSerializer::class)
@SerialName(ARTO.SERIAL_NAME)
data class ARTO(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ARTO"
    }
}


@KeepGeneratedSerializer
@Serializable(MATOSerializer::class)
@SerialName(MATO.SERIAL_NAME)
data class MATO(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MATO"
    }
}


@KeepGeneratedSerializer
@Serializable(MOVTSerializer::class)
@SerialName(MOVT.SERIAL_NAME)
data class MOVT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "MOVT"
    }
}


@KeepGeneratedSerializer
@Serializable(SNDRSerializer::class)
@SerialName(SNDR.SERIAL_NAME)
data class SNDR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SNDR"
    }
}


@KeepGeneratedSerializer
@Serializable(DUALSerializer::class)
@SerialName(DUAL.SERIAL_NAME)
data class DUAL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "DUAL"
    }
}


@KeepGeneratedSerializer
@Serializable(SNCTSerializer::class)
@SerialName(SNCT.SERIAL_NAME)
data class SNCT(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SNCT"
    }
}


@KeepGeneratedSerializer
@Serializable(SOPMSerializer::class)
@SerialName(SOPM.SERIAL_NAME)
data class SOPM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "SOPM"
    }
}


@KeepGeneratedSerializer
@Serializable(COLLSerializer::class)
@SerialName(COLL.SERIAL_NAME)
data class COLL(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "COLL"
    }
}


@KeepGeneratedSerializer
@Serializable(CLFMSerializer::class)
@SerialName(CLFM.SERIAL_NAME)
data class CLFM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "CLFM"
    }
}


@KeepGeneratedSerializer
@Serializable(REVBSerializer::class)
@SerialName(REVB.SERIAL_NAME)
data class REVB(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "REVB"
    }
}

@KeepGeneratedSerializer
@Serializable(REFRSerializer::class)
@SerialName(REFR.SERIAL_NAME)
data class REFR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "REFR"
    }
}

@KeepGeneratedSerializer
@Serializable(ACHRSerializer::class)
@SerialName(ACHR.SERIAL_NAME)
data class ACHR(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "ACHR"
    }
}

@KeepGeneratedSerializer
@Serializable(NAVMSerializer::class)
@SerialName(NAVM.SERIAL_NAME)
data class NAVM(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "NAVM"
    }
}


@KeepGeneratedSerializer
@Serializable(PGRESerializer::class)
@SerialName(PGRE.SERIAL_NAME)
data class PGRE(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "PGRE"
    }
}

@KeepGeneratedSerializer
@Serializable(PHZDSerializer::class)
@SerialName(PHZD.SERIAL_NAME)
data class PHZD(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "PHZD"
    }
}

@KeepGeneratedSerializer
@Serializable(LANDSerializer::class)
@SerialName(LAND.SERIAL_NAME)
data class LAND(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "LAND"
    }
}

@KeepGeneratedSerializer
@Serializable(INFOSerializer::class)
@SerialName(INFO.SERIAL_NAME)
data class INFO(
    override val header: RecordHeader,
    override val fields: RecordValue,
) : PluginRecord {
    companion object {
        const val SERIAL_NAME = "INFO"
    }
}
