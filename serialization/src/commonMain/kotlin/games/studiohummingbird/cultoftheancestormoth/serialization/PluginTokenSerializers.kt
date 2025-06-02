/**
Cult of the Ancestor Moth (PluginTokenSerializers.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.AACT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ACHR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ACTI
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ADDN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ALCH
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.AMMO
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ANIO
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.APPA
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ARMA
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ARMO
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ARTO
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ASPC
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ASTP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.AVIF
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.BOOK
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.BPTD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CAMS
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CELL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CLAS
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CLDC
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CLFM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CLMT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.COBJ
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.COLL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CONT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CPTH
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CSTY
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DEBR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DIAL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DLBR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DLVW
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DOBJ
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DOOR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.DUAL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ECZN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.EFSH
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.ENCH
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.EQUP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.EXPL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.EYES
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FACT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FLOR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FLST
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FSTP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FSTS
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FURN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GLOB
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GMST
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GRAS
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.HAIR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.HAZD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.HDPT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.IDLE
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.IDLM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.IMAD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.IMGS
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.INFO
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.INGR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.IPCT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.IPDS
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.KEYM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.KYWD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LAND
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LCRT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LCTN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LGTM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LIGH
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LSCR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LTEX
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LVLI
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LVLN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LVSP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MATO
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MATT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MESG
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MGEF
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MISC
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MOVT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MSTT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MUSC
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.MUST
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.NAVI
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.NAVM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.NPC
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.OTFT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PACK
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PERK
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PGRE
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PHZD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PROJ
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PWAT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginToken
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.QUST
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RACE
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.REFR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.REGN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RELA
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.REVB
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RFCT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RGDL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SCEN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SCOL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SCPT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SCRL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SHOU
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SLGM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SMBN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SMEN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SMQN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SNCT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SNDR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SOPM
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SOUN
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SPEL
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SPGD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.STAT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.TACT
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.TES4
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.TREE
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.TXST
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.VTYP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.WATR
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.WEAP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.WOOP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.WRLD
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.WTHR
import kotlinx.io.Buffer
import kotlinx.io.write
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val polymorphicPluginToken = SerializersModule {
    polymorphic(PluginToken::class) {
        subclass(TES4::class, TES4.serializer())
        subclass(GRUP::class, GRUP.serializer())
        subclass(GMST::class, GMST.serializer())
        subclass(KYWD::class, KYWD.serializer())
        subclass(LCRT::class, LCRT.serializer())
        subclass(AACT::class, AACT.serializer())
        subclass(TXST::class, TXST.serializer())
        subclass(GLOB::class, GLOB.serializer())
        subclass(CLAS::class, CLAS.serializer())
        subclass(FACT::class, FACT.serializer())
        subclass(HDPT::class, HDPT.serializer())
        subclass(HAIR::class, HAIR.serializer())
        subclass(EYES::class, EYES.serializer())
        subclass(RACE::class, RACE.serializer())
        subclass(SOUN::class, SOUN.serializer())
        subclass(ASPC::class, ASPC.serializer())
        subclass(MGEF::class, MGEF.serializer())
        subclass(SCPT::class, SCPT.serializer())
        subclass(LTEX::class, LTEX.serializer())
        subclass(ENCH::class, ENCH.serializer())
        subclass(SPEL::class, SPEL.serializer())
        subclass(SCRL::class, SCRL.serializer())
        subclass(ACTI::class, ACTI.serializer())
        subclass(TACT::class, TACT.serializer())
        subclass(ARMO::class, ARMO.serializer())
        subclass(BOOK::class, BOOK.serializer())
        subclass(CONT::class, CONT.serializer())
        subclass(DOOR::class, DOOR.serializer())
        subclass(INGR::class, INGR.serializer())
        subclass(LIGH::class, LIGH.serializer())
        subclass(MISC::class, MISC.serializer())
        subclass(APPA::class, APPA.serializer())
        subclass(STAT::class, STAT.serializer())
        subclass(SCOL::class, SCOL.serializer())
        subclass(MSTT::class, MSTT.serializer())
        subclass(PWAT::class, PWAT.serializer())
        subclass(GRAS::class, GRAS.serializer())
        subclass(TREE::class, TREE.serializer())
        subclass(CLDC::class, CLDC.serializer())
        subclass(FLOR::class, FLOR.serializer())
        subclass(FURN::class, FURN.serializer())
        subclass(WEAP::class, WEAP.serializer())
        subclass(AMMO::class, AMMO.serializer())
        subclass(NPC::class, NPC.serializer())
        subclass(LVLN::class, LVLN.serializer())
        subclass(KEYM::class, KEYM.serializer())
        subclass(ALCH::class, ALCH.serializer())
        subclass(IDLM::class, IDLM.serializer())
        subclass(COBJ::class, COBJ.serializer())
        subclass(PROJ::class, PROJ.serializer())
        subclass(HAZD::class, HAZD.serializer())
        subclass(SLGM::class, SLGM.serializer())
        subclass(LVLI::class, LVLI.serializer())
        subclass(WTHR::class, WTHR.serializer())
        subclass(CLMT::class, CLMT.serializer())
        subclass(SPGD::class, SPGD.serializer())
        subclass(RFCT::class, RFCT.serializer())
        subclass(REGN::class, REGN.serializer())
        subclass(NAVI::class, NAVI.serializer())
        subclass(CELL::class, CELL.serializer())
        subclass(WRLD::class, WRLD.serializer())
        subclass(DIAL::class, DIAL.serializer())
        subclass(QUST::class, QUST.serializer())
        subclass(IDLE::class, IDLE.serializer())
        subclass(PACK::class, PACK.serializer())
        subclass(CSTY::class, CSTY.serializer())
        subclass(LSCR::class, LSCR.serializer())
        subclass(LVSP::class, LVSP.serializer())
        subclass(ANIO::class, ANIO.serializer())
        subclass(WATR::class, WATR.serializer())
        subclass(EFSH::class, EFSH.serializer())
        subclass(EXPL::class, EXPL.serializer())
        subclass(DEBR::class, DEBR.serializer())
        subclass(IMGS::class, IMGS.serializer())
        subclass(IMAD::class, IMAD.serializer())
        subclass(FLST::class, FLST.serializer())
        subclass(PERK::class, PERK.serializer())
        subclass(BPTD::class, BPTD.serializer())
        subclass(ADDN::class, ADDN.serializer())
        subclass(AVIF::class, AVIF.serializer())
        subclass(CAMS::class, CAMS.serializer())
        subclass(CPTH::class, CPTH.serializer())
        subclass(VTYP::class, VTYP.serializer())
        subclass(MATT::class, MATT.serializer())
        subclass(IPCT::class, IPCT.serializer())
        subclass(IPDS::class, IPDS.serializer())
        subclass(ARMA::class, ARMA.serializer())
        subclass(ECZN::class, ECZN.serializer())
        subclass(LCTN::class, LCTN.serializer())
        subclass(MESG::class, MESG.serializer())
        subclass(RGDL::class, RGDL.serializer())
        subclass(DOBJ::class, DOBJ.serializer())
        subclass(LGTM::class, LGTM.serializer())
        subclass(MUSC::class, MUSC.serializer())
        subclass(FSTP::class, FSTP.serializer())
        subclass(FSTS::class, FSTS.serializer())
        subclass(SMBN::class, SMBN.serializer())
        subclass(SMQN::class, SMQN.serializer())
        subclass(SMEN::class, SMEN.serializer())
        subclass(DLBR::class, DLBR.serializer())
        subclass(MUST::class, MUST.serializer())
        subclass(DLVW::class, DLVW.serializer())
        subclass(WOOP::class, WOOP.serializer())
        subclass(SHOU::class, SHOU.serializer())
        subclass(EQUP::class, EQUP.serializer())
        subclass(RELA::class, RELA.serializer())
        subclass(SCEN::class, SCEN.serializer())
        subclass(ASTP::class, ASTP.serializer())
        subclass(OTFT::class, OTFT.serializer())
        subclass(ARTO::class, ARTO.serializer())
        subclass(MATO::class, MATO.serializer())
        subclass(MOVT::class, MOVT.serializer())
        subclass(SNDR::class, SNDR.serializer())
        subclass(DUAL::class, DUAL.serializer())
        subclass(SNCT::class, SNCT.serializer())
        subclass(SOPM::class, SOPM.serializer())
        subclass(COLL::class, COLL.serializer())
        subclass(CLFM::class, CLFM.serializer())
        subclass(REVB::class, REVB.serializer())
        subclass(REFR::class, REFR.serializer())
        subclass(ACHR::class, ACHR.serializer())
        subclass(NAVM::class, NAVM.serializer())
        subclass(PGRE::class, PGRE.serializer())
        subclass(PHZD::class, PHZD.serializer())
        subclass(LAND::class, LAND.serializer())
        subclass(INFO::class, INFO.serializer())
    }
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
object GRUPSerializer : KSerializer<GRUP> {
    override val descriptor: SerialDescriptor = GRUP.generatedSerializer().descriptor

    override fun serialize(
        encoder: Encoder,
        value: GRUP
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): GRUP {
        require(decoder is ByteStringDecoder)
        return decoder.decodeStructure(descriptor) {
            val buffer = Buffer()

            val headerByteString = decoder.decodeByteString(20)
            val header = PluginFormat.decodeFromByteString(GroupHeader.serializer(), headerByteString)
            buffer.write(headerByteString)

            val groupValueSize = header.groupSize.uint.toInt() - 24
            val groupValueByteString = decoder.decodeByteString(groupValueSize)
            buffer.write(groupValueByteString)

            PluginFormat.decodeFromSource(GRUP.generatedSerializer(), buffer)
        }
    }
}

object TES4Serializer : PluginRecordSerializer<TES4>(TES4.generatedSerializer())
object GMSTSerializer : PluginRecordSerializer<GMST>(GMST.generatedSerializer())
object KYWDSerializer : PluginRecordSerializer<KYWD>(KYWD.generatedSerializer())
object LCRTSerializer : PluginRecordSerializer<LCRT>(LCRT.generatedSerializer())
object AACTSerializer : PluginRecordSerializer<AACT>(AACT.generatedSerializer())
object TXSTSerializer : PluginRecordSerializer<TXST>(TXST.generatedSerializer())
object GLOBSerializer : PluginRecordSerializer<GLOB>(GLOB.generatedSerializer())
object CLASSerializer : PluginRecordSerializer<CLAS>(CLAS.generatedSerializer())
object FACTSerializer : PluginRecordSerializer<FACT>(FACT.generatedSerializer())
object HDPTSerializer : PluginRecordSerializer<HDPT>(HDPT.generatedSerializer())
object HAIRSerializer : PluginRecordSerializer<HAIR>(HAIR.generatedSerializer())
object EYESSerializer : PluginRecordSerializer<EYES>(EYES.generatedSerializer())
object RACESerializer : PluginRecordSerializer<RACE>(RACE.generatedSerializer())
object SOUNSerializer : PluginRecordSerializer<SOUN>(SOUN.generatedSerializer())
object ASPCSerializer : PluginRecordSerializer<ASPC>(ASPC.generatedSerializer())
object MGEFSerializer : PluginRecordSerializer<MGEF>(MGEF.generatedSerializer())
object SCPTSerializer : PluginRecordSerializer<SCPT>(SCPT.generatedSerializer())
object LTEXSerializer : PluginRecordSerializer<LTEX>(LTEX.generatedSerializer())
object ENCHSerializer : PluginRecordSerializer<ENCH>(ENCH.generatedSerializer())
object SPELSerializer : PluginRecordSerializer<SPEL>(SPEL.generatedSerializer())
object SCRLSerializer : PluginRecordSerializer<SCRL>(SCRL.generatedSerializer())
object ACTISerializer : PluginRecordSerializer<ACTI>(ACTI.generatedSerializer())
object TACTSerializer : PluginRecordSerializer<TACT>(TACT.generatedSerializer())
object ARMOSerializer : PluginRecordSerializer<ARMO>(ARMO.generatedSerializer())
object BOOKSerializer : PluginRecordSerializer<BOOK>(BOOK.generatedSerializer())
object CONTSerializer : PluginRecordSerializer<CONT>(CONT.generatedSerializer())
object DOORSerializer : PluginRecordSerializer<DOOR>(DOOR.generatedSerializer())
object INGRSerializer : PluginRecordSerializer<INGR>(INGR.generatedSerializer())
object LIGHSerializer : PluginRecordSerializer<LIGH>(LIGH.generatedSerializer())
object MISCSerializer : PluginRecordSerializer<MISC>(MISC.generatedSerializer())
object APPASerializer : PluginRecordSerializer<APPA>(APPA.generatedSerializer())
object STATSerializer : PluginRecordSerializer<STAT>(STAT.generatedSerializer())
object SCOLSerializer : PluginRecordSerializer<SCOL>(SCOL.generatedSerializer())
object MSTTSerializer : PluginRecordSerializer<MSTT>(MSTT.generatedSerializer())
object PWATSerializer : PluginRecordSerializer<PWAT>(PWAT.generatedSerializer())
object GRASSerializer : PluginRecordSerializer<GRAS>(GRAS.generatedSerializer())
object TREESerializer : PluginRecordSerializer<TREE>(TREE.generatedSerializer())
object CLDCSerializer : PluginRecordSerializer<CLDC>(CLDC.generatedSerializer())
object FLORSerializer : PluginRecordSerializer<FLOR>(FLOR.generatedSerializer())
object FURNSerializer : PluginRecordSerializer<FURN>(FURN.generatedSerializer())
object WEAPSerializer : PluginRecordSerializer<WEAP>(WEAP.generatedSerializer())
object AMMOSerializer : PluginRecordSerializer<AMMO>(AMMO.generatedSerializer())
object NPCSerializer : PluginRecordSerializer<NPC>(NPC.generatedSerializer())
object LVLNSerializer : PluginRecordSerializer<LVLN>(LVLN.generatedSerializer())
object KEYMSerializer : PluginRecordSerializer<KEYM>(KEYM.generatedSerializer())
object ALCHSerializer : PluginRecordSerializer<ALCH>(ALCH.generatedSerializer())
object IDLMSerializer : PluginRecordSerializer<IDLM>(IDLM.generatedSerializer())
object COBJSerializer : PluginRecordSerializer<COBJ>(COBJ.generatedSerializer())
object PROJSerializer : PluginRecordSerializer<PROJ>(PROJ.generatedSerializer())
object HAZDSerializer : PluginRecordSerializer<HAZD>(HAZD.generatedSerializer())
object SLGMSerializer : PluginRecordSerializer<SLGM>(SLGM.generatedSerializer())
object LVLISerializer : PluginRecordSerializer<LVLI>(LVLI.generatedSerializer())
object WTHRSerializer : PluginRecordSerializer<WTHR>(WTHR.generatedSerializer())
object CLMTSerializer : PluginRecordSerializer<CLMT>(CLMT.generatedSerializer())
object SPGDSerializer : PluginRecordSerializer<SPGD>(SPGD.generatedSerializer())
object RFCTSerializer : PluginRecordSerializer<RFCT>(RFCT.generatedSerializer())
object REGNSerializer : PluginRecordSerializer<REGN>(REGN.generatedSerializer())
object NAVISerializer : PluginRecordSerializer<NAVI>(NAVI.generatedSerializer())
object CELLSerializer : PluginRecordSerializer<CELL>(CELL.generatedSerializer())
object WRLDSerializer : PluginRecordSerializer<WRLD>(WRLD.generatedSerializer())
object DIALSerializer : PluginRecordSerializer<DIAL>(DIAL.generatedSerializer())
object QUSTSerializer : PluginRecordSerializer<QUST>(QUST.generatedSerializer())
object IDLESerializer : PluginRecordSerializer<IDLE>(IDLE.generatedSerializer())
object PACKSerializer : PluginRecordSerializer<PACK>(PACK.generatedSerializer())
object CSTYSerializer : PluginRecordSerializer<CSTY>(CSTY.generatedSerializer())
object LSCRSerializer : PluginRecordSerializer<LSCR>(LSCR.generatedSerializer())
object LVSPSerializer : PluginRecordSerializer<LVSP>(LVSP.generatedSerializer())
object ANIOSerializer : PluginRecordSerializer<ANIO>(ANIO.generatedSerializer())
object WATRSerializer : PluginRecordSerializer<WATR>(WATR.generatedSerializer())
object EFSHSerializer : PluginRecordSerializer<EFSH>(EFSH.generatedSerializer())
object EXPLSerializer : PluginRecordSerializer<EXPL>(EXPL.generatedSerializer())
object DEBRSerializer : PluginRecordSerializer<DEBR>(DEBR.generatedSerializer())
object IMGSSerializer : PluginRecordSerializer<IMGS>(IMGS.generatedSerializer())
object IMADSerializer : PluginRecordSerializer<IMAD>(IMAD.generatedSerializer())
object FLSTSerializer : PluginRecordSerializer<FLST>(FLST.generatedSerializer())
object PERKSerializer : PluginRecordSerializer<PERK>(PERK.generatedSerializer())
object BPTDSerializer : PluginRecordSerializer<BPTD>(BPTD.generatedSerializer())
object ADDNSerializer : PluginRecordSerializer<ADDN>(ADDN.generatedSerializer())
object AVIFSerializer : PluginRecordSerializer<AVIF>(AVIF.generatedSerializer())
object CAMSSerializer : PluginRecordSerializer<CAMS>(CAMS.generatedSerializer())
object CPTHSerializer : PluginRecordSerializer<CPTH>(CPTH.generatedSerializer())
object VTYPSerializer : PluginRecordSerializer<VTYP>(VTYP.generatedSerializer())
object MATTSerializer : PluginRecordSerializer<MATT>(MATT.generatedSerializer())
object IPCTSerializer : PluginRecordSerializer<IPCT>(IPCT.generatedSerializer())
object IPDSSerializer : PluginRecordSerializer<IPDS>(IPDS.generatedSerializer())
object ARMASerializer : PluginRecordSerializer<ARMA>(ARMA.generatedSerializer())
object ECZNSerializer : PluginRecordSerializer<ECZN>(ECZN.generatedSerializer())
object LCTNSerializer : PluginRecordSerializer<LCTN>(LCTN.generatedSerializer())
object MESGSerializer : PluginRecordSerializer<MESG>(MESG.generatedSerializer())
object RGDLSerializer : PluginRecordSerializer<RGDL>(RGDL.generatedSerializer())
object DOBJSerializer : PluginRecordSerializer<DOBJ>(DOBJ.generatedSerializer())
object LGTMSerializer : PluginRecordSerializer<LGTM>(LGTM.generatedSerializer())
object MUSCSerializer : PluginRecordSerializer<MUSC>(MUSC.generatedSerializer())
object FSTPSerializer : PluginRecordSerializer<FSTP>(FSTP.generatedSerializer())
object FSTSSerializer : PluginRecordSerializer<FSTS>(FSTS.generatedSerializer())
object SMBNSerializer : PluginRecordSerializer<SMBN>(SMBN.generatedSerializer())
object SMQNSerializer : PluginRecordSerializer<SMQN>(SMQN.generatedSerializer())
object SMENSerializer : PluginRecordSerializer<SMEN>(SMEN.generatedSerializer())
object DLBRSerializer : PluginRecordSerializer<DLBR>(DLBR.generatedSerializer())
object MUSTSerializer : PluginRecordSerializer<MUST>(MUST.generatedSerializer())
object DLVWSerializer : PluginRecordSerializer<DLVW>(DLVW.generatedSerializer())
object WOOPSerializer : PluginRecordSerializer<WOOP>(WOOP.generatedSerializer())
object SHOUSerializer : PluginRecordSerializer<SHOU>(SHOU.generatedSerializer())
object EQUPSerializer : PluginRecordSerializer<EQUP>(EQUP.generatedSerializer())
object RELASerializer : PluginRecordSerializer<RELA>(RELA.generatedSerializer())
object SCENSerializer : PluginRecordSerializer<SCEN>(SCEN.generatedSerializer())
object ASTPSerializer : PluginRecordSerializer<ASTP>(ASTP.generatedSerializer())
object OTFTSerializer : PluginRecordSerializer<OTFT>(OTFT.generatedSerializer())
object ARTOSerializer : PluginRecordSerializer<ARTO>(ARTO.generatedSerializer())
object MATOSerializer : PluginRecordSerializer<MATO>(MATO.generatedSerializer())
object MOVTSerializer : PluginRecordSerializer<MOVT>(MOVT.generatedSerializer())
object SNDRSerializer : PluginRecordSerializer<SNDR>(SNDR.generatedSerializer())
object DUALSerializer : PluginRecordSerializer<DUAL>(DUAL.generatedSerializer())
object SNCTSerializer : PluginRecordSerializer<SNCT>(SNCT.generatedSerializer())
object SOPMSerializer : PluginRecordSerializer<SOPM>(SOPM.generatedSerializer())
object COLLSerializer : PluginRecordSerializer<COLL>(COLL.generatedSerializer())
object CLFMSerializer : PluginRecordSerializer<CLFM>(CLFM.generatedSerializer())
object REVBSerializer : PluginRecordSerializer<REVB>(REVB.generatedSerializer())
object REFRSerializer : PluginRecordSerializer<REFR>(REFR.generatedSerializer())
object ACHRSerializer : PluginRecordSerializer<ACHR>(ACHR.generatedSerializer())
object NAVMSerializer : PluginRecordSerializer<NAVM>(NAVM.generatedSerializer())
object PGRESerializer : PluginRecordSerializer<PGRE>(PGRE.generatedSerializer())
object PHZDSerializer : PluginRecordSerializer<PHZD>(PHZD.generatedSerializer())
object LANDSerializer : PluginRecordSerializer<LAND>(LAND.generatedSerializer())
object INFOSerializer : PluginRecordSerializer<INFO>(INFO.generatedSerializer())
